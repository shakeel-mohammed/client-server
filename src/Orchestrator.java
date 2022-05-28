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
            case "opt-bf":
                runWithOptimizedBestFit();
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

    private void runWithOptimizedBestFit() {
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
