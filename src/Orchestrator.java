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
            case "ls":
                runWithLargestServers();
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
        while (!(event = clientServerConnection.sendMessage("REDY")).contains("NONE")) { // we're in trouble if any other response contains this substring
            if (event.trim().equals("ERR")) {
                System.out.println("encountered an error: " + event);
                break;
            }
            System.out.println("new response to REDY: " + event);
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
                //boolean isTargetTheMostRecentlyUsed = target.getID() == mostRecentlyUsedServer.getID() && target.getServerType().equals(mostRecentlyUsedServer.getServerType());
                if (!target.canHandleJobImmediately(job) || (target.isBooting() && (mostRecentlyUsedServer.getID() != target.getID() && !mostRecentlyUsedServer.getServerType().equals(target.getServerType())))) {

                    // if this job has already been pushed, we either migrate a job from the target server,
                    // or we simply queue the job to the server with the lowest estimated wait time.
                    // for now, we'll just queue it to the first server that can handle it.
                    if (job.hasBeenReSubmitted()) {
                        SimulatedServer nextLargest = simulatedSystem.findNextServerByType(largestServerType, mostRecentlyUsedServer);
                        // in the future, we find the server with the lowest est wait time
                        System.out.println("found available server to handle re-submission:");
                        nextLargest.display();
        
                        nextLargest.scheduleJob(job.getID());
                        this.mostRecentlyUsedServer = nextLargest;
                        continue;
                    }

                    // this actually doesn't "step-down" for the next biggest server type if all largest servers are busy...
                    // for now, it'll cause the job to queue up
                    target = simulatedSystem.findNextServerByType(largestServerType, mostRecentlyUsedServer);

                    // this is a new job, if the new target cannot handle the job right away (not enough cores)
                    // find a cycle through the next biggest until we find a server that can handle it.
                    // if none, we'll push the job
                    // we can step down in server types later
                    
                    //SimulatedServer serverThatCanHandleJob = simulatedSystem.findServerWithMinimumCores(job.getCoresRequired());
                    // while(serverThatCanHandleJob = findServerWithMinimumCores(job.getCoresRequired()) == null) {

                    // }

                    SimulatedServer originalTarget = target;
                    boolean shouldPushJob = false;
                    
                    while (!target.canHandleJobImmediately(job)) {
                        // the new target cannot handle this job right now
                        // move to the next server laterally
                        SimulatedServer potentialTarget = simulatedSystem.findNextServerByType(largestServerType, target);
                        
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
                        simulatedSystem.pushCurrentJob();
                        continue;
                    }
                }
                
                System.out.println("found available server:");
                target.display();

                target.scheduleJob(job.getID());
                this.mostRecentlyUsedServer = target;
            }
        }
    }

    private void runWithLargestServers() {
        String event;
        while (!(event = clientServerConnection.sendMessage("REDY")).contains("NONE")) { // we're in trouble if any other response contains this substring
            if (event.trim().equals("ERR")) {
                System.out.println("encountered an error: " + event);
                break;
            }
            System.out.println("new response to REDY: " + event);
            Job job = new Job(new JobInformationBuilder(event, false).build());

            String largestServerType = "";

            if (!job.isComplete()) {
                String query = job.buildQueryForCapableServer();
                ArrayList<SimulatedServer> realtimeServerInformation = simulatedSystem.queryDSSim(query);
                simulatedSystem.setServerStore(realtimeServerInformation);

                SimulatedServer largest = simulatedSystem.getLargestServer();

                // these are set once and then persisted
                if (largestServerType.equals("")) largestServerType = largest.getServerType();

                SimulatedServer target = largest;

                // Not enough cores available, this job will be queued
                // eg. largest is supersilk, with 4 free cores but job needs 6
                // checking the booting stuff is really adding to the TAT. 9739.33 -> 11021.72
                if (target.getNumberOfCores() < job.getCoresRequired() || (target.isBooting() && (mostRecentlyUsedServer.getID() != target.getID() && !mostRecentlyUsedServer.getServerType().equals(target.getServerType())))) {
                    target = simulatedSystem.findNextServerByType(largestServerType, mostRecentlyUsedServer);
                }
                
                System.out.println("found available server:");
                target.display();

                target.scheduleJob(job.getID());
                this.mostRecentlyUsedServer = target;
            }
        }
    }

}
