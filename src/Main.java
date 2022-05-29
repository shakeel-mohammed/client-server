/**
 * Steps:
 * 1. handshake
 * 2. read the ds-system.xml file
 * 3. dispatch "REDY" to ds-sim server
 * 4. The server reply with any one of the following events
 * - JOBN - Job submission information
 * - JOBP - Job re-submission information after pre-emption
 * - JCPL - Job completion
 * - RESF - Server failure notice
 * - RESR - Server recovery notice
 * - NONE - No more jobs to schedule
 * - ERR - Error message
 * - DATA - ??
 * 5. handle the event
 * 
 * The client can also send the following commands to the ds-sim server
 * - GETS - Server information request
 * - SCHD - Scheduling decision
 * - CNTJ - count the number of jobs on a specified server with a particular
 * state
 * - EJWT - the total number of estimated wait time on a given server
 * - LSTJ - job list of a server. i.e all pending jobs (includes waiting and
 * running)
 * - PSHJ - Force to get the next job to schedule, skipping the current job
 * - MIGJ - migrate job from source server to distination server
 * - KILJ - kill a job
 * - TERM - Server termination
 */

public class Main {
    public static void main(String[] args) throws Exception {
        ConfigDataLoader configDataLoader = ConfigDataLoader.getInstance();
        ClientServerConnection clientServerConnection = ClientServerConnection.getInstance();
        Orchestrator orchestrator = new Orchestrator(configDataLoader.get("algorithm"));

        if (clientServerConnection.wasHandshakeSuccessful()) {
            System.out.println("Server connected!");
            orchestrator.run();
        }

        clientServerConnection.closeConnectionGracefully();
    }
}
