import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

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
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private SimulatedSystem simulatedSystem;

    public void createConnection(String ip, int port) {
        try {
            System.out.println("Connecting to server...");
            clientSocket = new Socket(ip, port);
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    public boolean establishConnectionWithHandShake () {
        // create a connection with the server
        this.createConnection("127.0.0.1", 50000);

        // begin handshake process
        // Step 1: Send HELO
        String responseToHello = this.sendMessage("HELO");
        if (!responseToHello.trim().equals("OK")) {
            return false;
        }
        System.out.println("Server responded to handshake correctly.");

        // Step 2: Send AUTH
        String responseToAuth = this.sendMessage("AUTH some_random_auth_str");
        if (!responseToAuth.trim().equals("OK")) {
            return false;
        }
        System.out.println("Server responded to auth correctly.");

        // server responded correctly in both steps. Connection established successfully
        return true;
    }

    public void closeConnectionGracefully () {
        try {
            System.out.println("Closing connection...");
            out.write(("QUIT\n").getBytes());
            out.flush();

            String resp = in.readLine();

            if (resp.trim().equals("QUIT")) {
                System.out.println("Closing connection gracefully");
                in.close();
                out.close();
                clientSocket.close();
                System.out.println("Connection closed");
            } else {;
                throw new Exception("Server did not respond to QUIT command correctly");
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public String sendMessage(String msg) {
        try {
            out.write((msg + "\n").getBytes());
            out.flush();
            String resp = in.readLine();
            return resp;
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            return "";
        }
    }

    public void getServerStateInformation(String query) {
        String responseToQuery = sendMessage(query);
        System.out.println("Indication Reponse to GETS: " + responseToQuery);
        String[] serverInformation = responseToQuery.split(" ");
        int numberOfServers = Integer.parseInt(serverInformation[1]);

        try {
            out.write("OK\n".getBytes());
            out.flush();

            byte[] buffer = new byte[1024];
            String resp = "";
            int read;
            while((read = in.read(buffer)) != -1) {
                resp = new String(buffer, 0, read);
                break;
            };
            simulatedSystem = new SimulatedSystem(numberOfServers, resp, in, out);
            System.out.println("Updated simulated server store...");
            simulatedSystem.printServerStore();

            String responseToOK = sendMessage("OK");
            if (!responseToOK.trim().equals(".")) {
                throw new Error("Unexpected ACK response from ds-sim server: " + resp);
            }
        } catch (Exception exc) {
            System.out.println("exception: " + exc);
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        boolean isConnectionEstablished = client.establishConnectionWithHandShake();


        System.out.println("Connection established? " + isConnectionEstablished);

        if (isConnectionEstablished) {
            System.out.println("Server connected!");

            String event;
            while (!(event = client.sendMessage("REDY")).contains("NONE")) { // we're in trouble if any other response contains this substring
                if (event.trim().equals("ERR")) {
                    System.out.println("encountered an error: " + event);
                    break;
                    // throw? catch somewhere else?
                }
                System.out.println("new response to REDY: " + event);
                Job job = new Job(new JobInformationBuilder(event, false).build());

                System.out.println("Job is complete? " + job.isComplete());

                if (!job.isComplete()) {
                    String serverInformationQuery = 
                        "GETS Capable " + 
                            job.getCoresRequired() + 
                            " " + job.getMemoryRequired() + 
                            " " + job.getDiskRequired();
    
                    client.getServerStateInformation(serverInformationQuery);
        
                    SimulatedServer largestServer = client.simulatedSystem.getLargestServer();
                    System.out.println("Displaying information for largest server...");
                    largestServer.display();
                    largestServer.scheduleJob(job.getID());
                    largestServer.queryJobList();
                    largestServer.displayJobList();
                }

                Thread.sleep(1000);
            }
        }

        client.closeConnectionGracefully();
    }
}
