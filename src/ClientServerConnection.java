import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.io.IOException;

public class ClientServerConnection {
    private static ClientServerConnection clientServerConnection = null;
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;

    private String ip;
    private int port;
    private boolean isHandshakeSuccessful;

    ClientServerConnection(String ip, int port) {
        this.ip = ip;
        this.port = port;
        this.setupConnection();
        this.isHandshakeSuccessful = this.handshake();
    }

    public void setupConnection() {
        try {
            System.out.println("Connecting to server...");
            clientSocket = new Socket(this.ip, this.port);
            in = new DataInputStream(this.clientSocket.getInputStream());
            out = new DataOutputStream(this.clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Unable to establish connection with server. Exception: " + e);
        }
    }

    public DataInputStream getDataInputStream() {
        return in;
    }

    public DataOutputStream getDataOutputStream() {
        return out;
    }

    public Boolean wasHandshakeSuccessful() {
        return this.isHandshakeSuccessful;
    }

    public static ClientServerConnection getInstance() {
        if (clientServerConnection == null) {
            clientServerConnection = new ClientServerConnection("127.0.0.1", 50000);
        }
        return clientServerConnection;
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


    public String sendMessage(String msg, Boolean isMultiLineResponse) {
        try {
            if (!isMultiLineResponse) return this.sendMessage(msg);
            out.write((msg + "\n").getBytes());
            out.flush();

            String resp = "";
            byte[] buffer = new byte[2048]; // TODO: find a better way to allocate size. should be dynamic. Maybe array of arrays?
            int read;
            while((read = in.read(buffer)) != -1) {
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
            String responseToAuth = this.sendMessage("AUTH some_random_auth_str");
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
}
