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
    private Job[] jobList;

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

    public void queryJobList() {
        try {
            String command = "LSTJ " + this.serverType + " " + this.serverID + "\n";
            String responseToQuery = sendMessage(command);
            System.out.println("Indication Reponse to LSTJ: " + responseToQuery);

            String[] jobsIndicativeInformation = responseToQuery.split(" ");
            int numberOfJobs = Integer.parseInt(jobsIndicativeInformation[1]);

            out.write("OK\n".getBytes());
            out.flush();

            byte[] buffer = new byte[1024];
            String resp = "";
            int read;
            while((read = in.read(buffer)) != -1) {
                resp = new String(buffer, 0, read);
                break;
            };

            this.jobList = new Job[numberOfJobs];

            String[] jobs = resp.split("\n");

            for (int i = 0; i < this.jobList.length; i++) {
                Job job = new Job(new JobInformationBuilder(jobs[i], true).build());
                this.jobList[i] = job;
            }

            String responseToOK = sendMessage("OK");
            if (!responseToOK.trim().equals(".")) {
                throw new Error("Unexpected ACK response from ds-sim server: " + resp);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e);
        }
    }

    public void displayJobList() {
        System.out.println("======= Displaying Job List ========");
        for(Job job : this.jobList) {
            job.display();
        }
        System.out.println("======= Complete ========");
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
