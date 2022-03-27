
public class Orchestrator {
    private ClientServerConnection connection = ClientServerConnection.getInstance();
    private SimulatedSystem simulatedSystem = new SimulatedSystem();
    private String largestServerType;
    private SimulatedServer mostRecentlyUsedServer = null;
    private String algorithm = "lrr";

    public Orchestrator(String algorithm) {
        this.algorithm = algorithm.toLowerCase();
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

    public void runWithLargestRoundRobin() {
        String event;
        while (!(event = connection.sendMessage("REDY")).contains("NONE")) { // we're in trouble if any other response contains this substring
            if (event.trim().equals("ERR")) {
                System.out.println("encountered an error: " + event);
                break;
                // throw? catch somewhere else?
            }
            System.out.println("new response to REDY: " + event);
            Job job = new Job(new JobInformationBuilder(event, false).build());

            if (!job.isComplete()) {
                String query = job.buildQueryForCapableServer();
                simulatedSystem.queryDSSim(query);

                this.largestServerType = simulatedSystem.getTypeOfLargestServer();
    
                SimulatedServer serverToScheduleJob = simulatedSystem.findNextServerByType(this.largestServerType, mostRecentlyUsedServer);
                serverToScheduleJob.scheduleJob(job.getID());
                this.mostRecentlyUsedServer = serverToScheduleJob;
            }
        }
    }

}
