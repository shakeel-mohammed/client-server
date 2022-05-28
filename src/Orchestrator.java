import java.util.ArrayList;

public class Orchestrator {
    private ClientServerConnection clientServerConnection = ClientServerConnection.getInstance();
    private SimulatedSystem simulatedSystem = new SimulatedSystem();
    private String largestServerType;
    private SimulatedServer mostRecentlyUsedServer = null;
    private String algorithm;

    public Orchestrator(String algorithm) {
        this.algorithm = algorithm.toLowerCase().trim();
    }

    public void run() {
        switch (this.algorithm) {
            case "lrr":
                runWithLargestRoundRobin();
                break;
            case "fc":
                runWithFirstCapable();
                break;
            case "opt-lrr":
                runWithLargestRoundRobinOpt();
                break;
            case "opt-bf":
                bestFitOptimised();
                break;
            default:
                throw new Error("algorithm not set up");
        }
    }

    private void runWithLargestRoundRobin() {
        Boolean shouldDisplayJobList = false;
        String event;
        while (!(event = clientServerConnection.sendMessage("REDY")).contains("NONE")) { // we're in trouble if any other response contains this substring
            if (event.trim().equals("ERR")) {
                System.out.println("encountered an error: " + event);
                break;
            }
            System.out.println("new response to REDY: " + event);
            Job job = new Job(new JobInformationBuilder(event, false).build());

            if (!job.isComplete()) {
                String query = job.buildQueryForCapableServer();
                ArrayList<SimulatedServer> realtimeServerInformation = simulatedSystem.queryDSSim(query);
                simulatedSystem.setServerStore(realtimeServerInformation);


                this.largestServerType = simulatedSystem.getTypeOfLargestServer();
    
                SimulatedServer serverToScheduleJob = simulatedSystem.findNextServerByType(this.largestServerType, mostRecentlyUsedServer);
                
                System.out.println("found available server:");
                serverToScheduleJob.display();

                serverToScheduleJob.scheduleJob(job.getID());
                this.mostRecentlyUsedServer = serverToScheduleJob;

                if (shouldDisplayJobList) {
                    System.out.println("querying server job list...");
                    serverToScheduleJob.queryJobList();
                    serverToScheduleJob.displayJobList();
                }
            }
        }
    }

    private void runWithFirstCapable() {
        Boolean shouldDisplayJobList = false;
        String event;
        while (!(event = clientServerConnection.sendMessage("REDY")).contains("NONE")) { // we're in trouble if any other response contains this substring
            if (event.trim().equals("ERR")) {
                System.out.println("encountered an error: " + event);
                break;
            }
            System.out.println("new response to REDY: " + event);
            Job job = new Job(new JobInformationBuilder(event, false).build());

            if (!job.isComplete()) {
                String query = job.buildQueryForCapableServer();
                simulatedSystem.queryDSSim(query);
    
                SimulatedServer serverToScheduleJob = simulatedSystem.getServerStore().get(0);

                if (serverToScheduleJob == null) {
                    throw new Error("No server capable of handling job");
                }
                
                System.out.println("found available server:");
                serverToScheduleJob.display();

                serverToScheduleJob.scheduleJob(job.getID());
                this.mostRecentlyUsedServer = serverToScheduleJob;

                if (shouldDisplayJobList) {
                    System.out.println("querying server job list...");
                    serverToScheduleJob.queryJobList();
                    serverToScheduleJob.displayJobList();
                }
            }
        }
    }

    private void runWithLargestRoundRobinOpt() {
        String event;
        int jobPushCount = 0;
        while (!(event = clientServerConnection.sendMessage("REDY")).contains("NONE")) { // we're in trouble if any other response contains this substring
            if (event.trim().equals("ERR")) {
                System.out.println("encountered an error: " + event);
                break;
            }
            // System.out.println("new response to REDY: " + event);
            Job job = new Job(new JobInformationBuilder(event, false).build());
            String largestServerType = "";

            if (!job.isComplete()) {
                String query = job.buildQueryForCapableServer();
                ArrayList<SimulatedServer> realtimeServerInformation = simulatedSystem.queryDSSim(query);
                simulatedSystem.setServerStore(realtimeServerInformation);

                // // which of the largest does this give us? 
                // // should give is the largest that can most easily fit this job
                // SimulatedServer largest = simulatedSystem.getLargestServer();

                // get type of largest server
                largestServerType = simulatedSystem.getTypeOfLargestServer();

                // find server of largest type that has the lowest number of cores (the one we've been sending jobs to)
                SimulatedServer busiestLargestServer = simulatedSystem.getBusiestLargestServer();

                // // Set once and then persisted
                // if (largestServerType.equals("")) largestServerType = largest.getServerType();

                SimulatedServer target = busiestLargestServer;

                // Not enough cores available, this job will be queued
                // eg. largest is supersilk, with 4 free cores but job needs 6
                // checking the booting stuff is really adding to the TAT. 9739.33 -> 11021.72
                boolean isTargetTheMostRecentlyUsed = false;

                if (mostRecentlyUsedServer != null) {
                    boolean isSameId = target.getID() == mostRecentlyUsedServer.getID();
                    boolean isSameType = target.getServerType().equals(mostRecentlyUsedServer.getServerType());

                    if (isSameId && isSameType) isTargetTheMostRecentlyUsed = true;
                }

                
                System.out.println("target: ");
                target.display();
                System.out.println("can handle job? ");
                System.out.println(target.canHandleJobImmediately(job));


                if (!target.canHandleJobImmediately(job) || (target.isBooting() && !isTargetTheMostRecentlyUsed)) {
                    // if this job has already been pushed, we could migrate a job from the target server,
                    
                    // System.out.println("moving to next server");
                    if (job.hasBeenReSubmitted()) {
                        // SimulatedServer nextLargest = simulatedSystem.findNextServerByType(largestServerType, mostRecentlyUsedServer);
                        // in the future, we find the server with the lowest est wait time
                        // System.out.println("found available server to handle re-submission:");
                        // nextLargest.display();

                        // find server that can handle it soonest. If they're all busy, find 
                        // the one with the lowest wait time.

                        // this should come back with null if none available right now
                        SimulatedServer newTarget = simulatedSystem.findServerWithCoresAtleast(job.getCoresRequired());

                        // all servers that can handle the job right now are busy
                        // find the server with the lowest wait time
                        if (newTarget == null) {

                            // this could come back with a server that is not active
                            // maybe we can is filter out the inactive ones ?
                            newTarget = simulatedSystem.findServerWithLowestWaitTime();
                        }

                        // maybe step down
        
                        newTarget.scheduleJob(job.getID());
                        this.mostRecentlyUsedServer = newTarget;
                        continue;
                    }

                    // this actually doesn't "step-down" for the next biggest server type if all largest servers are busy...
                    // for now, it'll cause the job to queue up
                    target = simulatedSystem.findNextServerByType(largestServerType, target);
                    //target = simulatedSystem.findServerWithCoresAtleast(job.getCoresRequired());

                    // this is a new job, if the new target cannot handle the job right away (not enough cores)
                    // cycle through the next biggest until we find a server that can handle it.
                    // if none, we'll push the job (or step down in server types?)
                    // we can step down in server types later
                    
                    //SimulatedServer serverThatCanHandleJob = simulatedSystem.findServerWithMinimumCores(job.getCoresRequired());
                    // while(serverThatCanHandleJob = findServerWithMinimumCores(job.getCoresRequired()) == null) {

                    // }

                    // once we know that we'll need to boot up a new server
                    // if the boot time of the server is more than the estRunTime of the job
                    // push the current job for later.
                    // do we want to remember that we've pushed the job because the runTime was so small?
                    // we could probably squeeze it somewhere else later
                    // to do this, we should need to place ds-sim in the base directory of this project
                    // ds-sim should write the xml file
                    // we would read it and add a bootTime to each server

                    // otherwise, if a job on the server is due to finish after(?) this job could
                    // go to the server and finish, we can migrate the job

                    SimulatedServer originalTarget = target;
                    boolean shouldPushJob = false;
                    
                    while (!target.canHandleJobImmediately(job)) {
                        // the new target cannot handle this job right now
                        // move to the next server laterally
                        SimulatedServer potentialTarget = simulatedSystem.findNextServerByType(largestServerType, target);
                        
                        // SimulatedServer potentialTarget = simulatedSystem.findServerWithCoresAtleast(job.getCoresRequired());
                        // we've checked all largest servers
                        if (potentialTarget.equals(originalTarget)) {
                            shouldPushJob = true;
                            break;
                        }

                        target = potentialTarget;
                    }

                    // if this job cannot fit anywhere. ie. no largest servers are able to handle the job right now
                    // pushJ for next run and continue
                    if (shouldPushJob) {
                        jobPushCount += 1;
                        simulatedSystem.pushCurrentJob();
                        continue;
                    }
                }
                
                // System.out.println("found available server:");
                // target.display();

                target.scheduleJob(job.getID());
                this.mostRecentlyUsedServer = target;
            }
        }
        System.out.print("PUSHED JOB COUNT: ");
        System.out.println(jobPushCount);
        System.out.print("\n");
    }

    private void bestFitOptimised() {
        // gets capable based on job and sort all results by the number of waiting jobs (or estimated wait time?)
        // if there is no server that can handle the job immediately,
        // do gets capable and sort by number of cores. send the job to "least capable" server.
        String event;
        boolean processedFirstJob = false;
        int backupCounter = 0;
        while (!(event = clientServerConnection.sendMessage("REDY")).contains("NONE")) { // we're in trouble if any other response contains this substring
            if (event.trim().equals("ERR")) {
                System.out.println("encountered an error: " + event);
                break;
            }
            // System.out.println("new response to REDY: " + event);
            Job job = new Job(new JobInformationBuilder(event, false).build());

            if (!job.isComplete()) {
                String query = job.buildQueryForCapableServer();
                ArrayList<SimulatedServer> realtimeServerInformation = simulatedSystem.queryDSSim(query);
                simulatedSystem.setServerStore(realtimeServerInformation);

                // sort them by least amount of waiting jobs, regardless of core count
                // if none can handle right now
                ArrayList<SimulatedServer> servers = simulatedSystem.sortServersByFewestWaitingJobs(simulatedSystem.getServerStore());
                
                SimulatedServer targetServer;

                if (!processedFirstJob) {
                    targetServer = simulatedSystem.findLeastCapableServer(servers);
                } else {
                    targetServer = simulatedSystem.findServerThatCanHandleJobImmediately(servers);
                }

                // we could end up in a situation where the wait time on the active server
                // is longer than the boot time of a whole new server

                // we never come into this ... investigate.
                if (targetServer == null) {
                    // no servers can handle the job right now
                    // find least capable server
                    targetServer = simulatedSystem.findLeastCapableServer(servers);
                    backupCounter+=1;
                }
                
                // System.out.println("found available server:");
                // targetServer.display();

                targetServer.scheduleJob(job.getID());
                processedFirstJob = true;
            }
        }
        System.out.print("COUNT of no servers avail right now: ");
        System.out.println(backupCounter);
        System.out.print("\n");
    }
}
