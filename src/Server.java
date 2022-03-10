import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) {
        try {
            System.out.println("Starting sever");

            serverSocket = new ServerSocket(port);

            while (!serverSocket.isClosed()) {

                clientSocket = serverSocket.accept();
                System.out.println("Client acccepted...");
    
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    
                String inputLine;
                while((inputLine = in.readLine()) != null) {
                    System.out.println("new message from client: " + inputLine);
                    if (inputLine.equals(".")) {
                        System.out.println("saying bye...");
                        out.println("good bye");
                        clientSocket.close();
                        break;
                    }
                    out.println(inputLine); // this just echos back the same message. no logic
                }
            }
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    public void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("Exception: " + e);
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.start(6666);
    }
}
