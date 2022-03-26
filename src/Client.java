/**
 * Steps:
 * 1. handshake
 * 2. read the ds-system.xml file
 * 3. dispatch "REDY" to ds-sim server
 * 4. The server reply with any one of the following events
 *  - JOBN - Job submission information
 *  - JOBP - Job re-submission information after pre-emption
 *  - JCPL - Job completion
 *  - RESF - Server failure notice
 *  - RESR - Server recovery notice
 *  - NONE - No more jobs to schedule
 *  - ERR - Error message
 *  - DATA - ??
 * 5. handle the event
 * 
 * The client can also send the following commands to the ds-sim server
 * - GETS - Server information request
 * - SCHD - Scheduling decision
 * - CNTJ - count the number of jobs on a specified server with a particular state
 * - EJWT - the total number of estimated wait time on a given server
 * - LSTJ - job list of a server. i.e all pending jobs (includes waiting and running)
 * - PSHJ - Force to get the next job to schedule, skipping the current job
 * - MIGJ - migrate job from source server to distination server
 * - KILJ - kill a job
 * - TERM - Server termination
 */

public class Client {
    private ClientServerConnection connection = ClientServerConnection.getInstance();
    private SimulatedSystem simulatedSystem;

    // doing this here rather than in the SimulatedSystem because we want to based our responses off an updated system for each new job.
    public void getDSSystemInfo(String query) {
        String responseToQuery = connection.sendMessage(query);
        String[] serverInformation = responseToQuery.split(" ");
        int numberOfServers = Integer.parseInt(serverInformation[1]);

        try {
            String responseToOK1 = connection.sendMessage("OK", true);
            simulatedSystem = new SimulatedSystem(numberOfServers, responseToOK1);
            System.out.println("Updated simulated server store to...");
            simulatedSystem.printServerStore();

            String responseToOK2 = connection.sendMessage("OK");
            if (!responseToOK2.trim().equals(".")) {
                throw new Error("Unexpected ACK response from ds-sim server: " + responseToOK2);
            }
        } catch (Exception exc) {
            System.out.println("exception: " + exc);
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        ClientServerConnection connection = ClientServerConnection.getInstance();

        if (connection.wasHandshakeSuccessful()) {
            System.out.println("Server connected!");

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
                    client.getDSSystemInfo(query);
        
                    SimulatedServer largestServer = client.simulatedSystem.getLargestServer();
                    System.out.println("Largest server:");
                    largestServer.display();
                    largestServer.scheduleJob(job.getID());
                }
            }
        }

        connection.closeConnectionGracefully();
    }
}
