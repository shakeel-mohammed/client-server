import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
    private PrintWriter out;
    private BufferedReader in;

    public void createConnection(String ip, int port) {
        try {
            System.out.println("Connecting to server...");
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    public boolean initiateHandshake () {
        try {
            out.println("HELO");
            String resp = in.readLine();
            if (resp.equals("OK")) {
                System.out.println("Server responded to handshake correctly");
                return true;
            } else {;
                throw new Exception("Server did not respond to handshake correctly");
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return false;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return false;
        }
    }

    public boolean authenticate(String username) {
        try {
            String authString = "AUTH " + username;
            out.println(authString);
            String resp = in.readLine();
            if (!(resp.equals("OK"))) {
                throw new Exception("Unable to authenticate");
            }
            return true;
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            return false;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return false;
        }
    }

    public boolean establishConnectionWithHandShake () {
        // create a connection with the server
        this.createConnection("127.0.0.1", 6666);

        // begin handshake processs. 1. HELO, 2. AUTH
        boolean isHandshakeInitiatedSucessfully = this.initiateHandshake();
        boolean isAuthenticated = this.authenticate("some_random_auth_str");

        // response was appropriate in both steps. Connection established
        if (isHandshakeInitiatedSucessfully && isAuthenticated) {
            return true;
        } else {
            return false;
        }
    }

    public void terminateConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            System.out.println("Connection closed");
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void closeConnectionGracefully () {
        try {
            System.out.println("Closing connection...");
            out.println("QUIT");
            String resp = in.readLine();
            if (resp.equals("QUIT")) {
                System.out.println("Closing connection gracefully");
                terminateConnection();
            } else {;
                throw new Exception("Server did not respond to QUIT command correctly");
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

        public String sendReadyCommand() {
        try {
            System.out.println("Getting event...");
            out.println("REDY");
            String resp = in.readLine();
            return resp;
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            return "";
        }
    }

    public String sendMessage(String msg) {
        try {
            out.println(msg);
            String resp = in.readLine();
            return resp;
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            return "";
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        boolean isConnectionEstablished = client.establishConnectionWithHandShake();


        System.out.println("Connection established? " + isConnectionEstablished);

        String event;
        while (!((event = client.sendReadyCommand()) == "NONE")) {
            System.out.println("incoming event: " + event);
        }

        System.out.println("Server replied with NONE. No more jobs to schedule.");
        client.closeConnectionGracefully();
    }
}
