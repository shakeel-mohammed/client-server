
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
            case "bf":
                throw new Error("this algorithm has not yet been implemented");
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
                simulatedSystem.queryDSSim(query);

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

}
