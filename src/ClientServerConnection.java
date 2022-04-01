import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.io.IOException;

public class ClientServerConnection {
    private ConfigDataLoader configDataLoader = ConfigDataLoader.getInstance();
    private static ClientServerConnection clientServerConnection = null;
    private Socket clientSocket;
    private DataInputStream inputStream;
    private DataOutputStream oututStream;

    private String hostIp = configDataLoader.get("hostIp");
    private int hostPort = Integer.parseInt(configDataLoader.get("hostPort")); 
    private boolean isHandshakeSuccessful;

    ClientServerConnection() {
        this.setupConnection();
        this.isHandshakeSuccessful = this.handshake();
    }

    public void setupConnection() {
        try {
            System.out.println("Connecting to server...");
            clientSocket = new Socket(this.hostIp, this.hostPort);
            inputStream = new DataInputStream(this.clientSocket.getInputStream());
            oututStream = new DataOutputStream(this.clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Unable to establish connection with server. Exception: " + e);
        }
    }

    public DataInputStream getDataInputStream() {
        return inputStream;
    }

    public DataOutputStream getDataOutputStream() {
        return oututStream;
    }

    public Boolean wasHandshakeSuccessful() {
        return this.isHandshakeSuccessful;
    }

    public static ClientServerConnection getInstance() {
        if (clientServerConnection == null) {
            clientServerConnection = new ClientServerConnection();
        }
        return clientServerConnection;
    }

    public String sendMessage(String msg) {
        try {
            oututStream.write((msg + "\n").getBytes());
            oututStream.flush();
            
            String resp = inputStream.readLine();
            return resp;
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            return "";
        }
    }

    /**
     * method used to send a message to server and parse response. 
     * @param msg string to be sent to the server
     * @param buffer buffer used to write into. If this parameter is provided, our class will handle response as a multi-line response.
     */
    public String sendMessage(String msg, byte[] buffer) {
        try {
            if (buffer == null) return this.sendMessage(msg);
            oututStream.write((msg + "\n").getBytes());
            oututStream.flush();

            String resp = "";
            int read;
            while((read = inputStream.read(buffer)) != -1) {
                resp = new String(buffer, 0, read);
                break;
            }; 

            return resp;
        } catch (IOException e) {
            System.out.println("Exception: " + e);
            return "";
        }
    }

    public boolean handshake () {
        if (!isHandshakeSuccessful) {
            // begin handshake process
            // Step 1: Send HELO
            String responseToHello = this.sendMessage("HELO");
            if (!responseToHello.trim().equals("OK")) return false;
            System.out.println("Server responded to handshake correctly.");

            // Step 2: Send AUTH
            String responseToAuth = this.sendMessage("AUTH " + System.getProperty("user.name"));
            if (!responseToAuth.trim().equals("OK")) return false;
            System.out.println("Server responded to auth correctly.");

            this.isHandshakeSuccessful = true;
        }

        return this.isHandshakeSuccessful;
    }

    public void closeConnectionGracefully () {
        try {
            System.out.println("Closing connection...");
            String resp = this.sendMessage("QUIT");

            if (resp.trim().equals("QUIT")) {
                System.out.println("Closing connection gracefully");
                inputStream.close();
                oututStream.close();
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
}
