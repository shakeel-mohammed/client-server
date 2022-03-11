import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

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

    public void authenticate(String username) {
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

    public boolean establishConnectionWithHandShake (Client client) {
        // create a connection with the server
        client.createConnection("127.0.0.1", 6666);

        // begin handshake processs. 1. HELO, 2. AUTH
        boolean isHandshakeInitiatedSucessfully = client.initiateHandshake();
        boolean isAuthenticated = client.authenticate("some_random_auth_str");

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
        boolean isConnectionEstablished = establishConnectionWithHandShake(client);


        System.out.println("Connection established? " + isConnectionEstablished);

        // once the connection is established, we need to read the ds-system.xml file
        // this is how we know which resources are available

        System.out.println("Getting first event...");
        String event;

        // the event wont be null. It will be a "NONE" ..response?
        while (!(event = client.sendReadyCommand() != null)) {
            System.out.println("incoming event: " + event);
        }

        client.closeConnectionGracefully();
    }
}
