import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SimulatedServer {
    private String serverType;
    private int serverID;
    private String state;
    private int currentStartTime;
    private int core;
    private int memory;
    private int disk;
    private int numWaitingJobs;
    private int numRunningJobs;

    private DataInputStream in;
    private DataOutputStream out;

    SimulatedServer(String strigifiedServerInformation, DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;

        // break the string by the space between each attribute
        String[] serverInformation = strigifiedServerInformation.split(" ");
        
        // the location of each attribute is pre-determined. We can use this to correctly assign state variables.
        this.serverType = serverInformation[0];
        this.serverID = Integer.parseInt(serverInformation[1]);
        this.state = serverInformation[2];
        this.currentStartTime = Integer.parseInt(serverInformation[3]);
        this.core = Integer.parseInt(serverInformation[4]);
        this.memory = Integer.parseInt(serverInformation[5]);
        this.disk = Integer.parseInt(serverInformation[6]);
        this.numWaitingJobs = Integer.parseInt(serverInformation[7]);
        this.numRunningJobs = Integer.parseInt(serverInformation[8]);
    }

    public boolean isActive() {
        return this.state == "active";
    }

    public boolean isBooting() {
        return this.state == "booting";
    }

    public int getNumberOfCores() {
        return this.core;
    }

    public void scheduleJob(int jobID) {
        try {
            String command = "SCHD " + jobID + " " + this.serverType + " " + this.serverID + "\n";
            out.write(command.getBytes());
            out.flush();
            String resp = in.readLine();
            if (!resp.trim().equals("OK")) {
                throw new Error("Unexpected job scheduling response from ds-sim server: " + resp);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public void display() {
        System.out.println("================");
        System.out.println("Server Type: " + serverType);
        System.out.println("Server ID: " + serverID);
        System.out.println("Server State: " + state);
        System.out.println("Server current start time: " + currentStartTime);
        System.out.println("Server Core: " + core);
        System.out.println("Server Memory: " + memory);
        System.out.println("Server Disk: " + disk);
        System.out.println("Number of waiting jobs: " + numWaitingJobs);
        System.out.println("Number of running jobs: " + numRunningJobs);
        System.out.println("================");
    }
}
