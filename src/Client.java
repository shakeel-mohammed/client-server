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

    public boolean initiateHandshake () {
        try {
            out.write(("HELO\n").getBytes());
            out.flush();

            String resp = in.readLine();

            if (resp.trim().equals("OK")) {
                System.out.println("Server responded to handshake correctly");
                return true;
            } else {
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
            out.write((authString + "\n").getBytes());
            out.flush();

            String resp = in.readLine();

            if (!(resp.trim().equals("OK"))) {
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
        this.createConnection("127.0.0.1", 50000);

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
            out.write(("QUIT\n").getBytes());
            out.flush();

            String resp = in.readLine();

            if (resp.trim().equals("QUIT")) {
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
            out.write("REDY\n".getBytes());
            out.flush();

            String resp = in.readLine();
            return resp;
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            return "";
        }
    }

    public String sendOKCommand() {
        try {
            out.write("OK\n".getBytes());
            out.flush();

            String resp = in.readLine();
            return resp;
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            return "";
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

    public void getServerStateInformation() {
        String responseToGets = sendMessage("GETS All");
        System.out.println("Indication Reponse to GETS: " + responseToGets);

        // each available server is sent as a new line, with a newline character
        // eg. "juju 1 inactive -1 2 4000 16000 0 0\n"
        // we don't need to keep sending OK, we just need to keep reading from the stream.
        // once we receive a ".", we then send the OK response.

        try {
            out.write("OK\n".getBytes());
            out.flush();

            byte[] buffer = new byte[1024];
            String resp = "";
            int read;
            while((read = in.read(buffer)) != -1) {
                resp = new String(buffer, 0, read);
                System.out.println("ended");
                break;
            };
            System.out.println("all data: " + resp);
            // String event;
            // while ((event = sendOKCommand()).trim() != ".") {
            //     Thread.sleep(1000);
            //     if (event.equals("ERR")) {
            //         System.out.println("encountered an error.");
            //         break;
            //     } else {
            //         System.out.println("incoming event: " + event);
            //     }
            // }
            String responseToOK = sendOKCommand();
            System.out.println("responseToOK: " + responseToOK);
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

            String responseToREDY = client.sendMessage("REDY");
            System.out.println("Response to REDY: " + responseToREDY);

            client.getServerStateInformation();

            // String event;
            // while (!((event = client.sendReadyCommand()) == "NONE")) {
            //     if (event.equals("ERR")) {
            //         System.out.println("encountered an error.");
            //         break;
            //     } else {
            //         System.out.println("incoming event: " + event);
            //     }
            // }
        }

        client.closeConnectionGracefully();
    }
}
