import java.util.ArrayList;

public class SimulatedServer {
    private ConfigDataLoader configDataLoader = ConfigDataLoader.getInstance();
    private ClientServerConnection clientServerConnection = ClientServerConnection.getInstance();

    private String serverType;
    private int serverID;
    private String state;
    private int currentStartTime;
    private int core;
    private int memory;
    private int disk;
    private int numWaitingJobs;
    private int numRunningJobs;
    private ArrayList<Job> jobList = new ArrayList<Job>();

    SimulatedServer(String strigifiedServerInformation) {
        // break the string by the space between each attribute
        String[] serverInformation = strigifiedServerInformation.split(" ");

        // the location of each attribute is pre-determined. We can use this to
        // correctly assign state variables.
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

    public int getID() {
        return this.serverID;
    }

    public int getNumberOfCores() {
        return this.core;
    }

    public String getServerType() {
        return this.serverType.trim();
    }

    public boolean isBooting() {
        if (this.state.trim().equals("booting"))
            return true;
        return false;
    }

    public boolean isActive() {
        if (this.state.trim().equals("active"))
            return true;
        return false;
    }

    public int getNumWaitingJobs() {
        return this.numWaitingJobs;
    }

    public void scheduleJob(int jobID) {
        try {
            String command = "SCHD " + jobID + " " + this.serverType + " " + this.serverID;
            String resposeToJobSchedule = clientServerConnection.sendMessage(command);
            if (!resposeToJobSchedule.trim().equals("OK")) {
                throw new Error("Unexpected job scheduling response from ds-sim server: " + resposeToJobSchedule);
            }
        } catch (Exception e) {
            System.out.println("IOException: " + e);
        }
    }

    // can potentially be removed
    public void queryJobList() {
        try {
            String command = "LSTJ " + this.serverType + " " + this.serverID;
            String responseToQuery = clientServerConnection.sendMessage(command);

            String[] jobsIndicativeInformation = responseToQuery.split(" ");
            int numberOfJobs = Integer.parseInt(jobsIndicativeInformation[1]);
            int lengthPerRecord = Integer.parseInt(jobsIndicativeInformation[2]);

            // adding some buffer to record length
            int adjustedRecordLength = lengthPerRecord
                    + Integer.parseInt(configDataLoader.get("buffer_for_record_length"));
            byte[] buffer = new byte[numberOfJobs * adjustedRecordLength];

            String jobListString = clientServerConnection.sendMessage("OK", buffer);
            String[] jobs = jobListString.split("\n");

            for (String job : jobs)
                this.jobList.add(new Job(new JobInformationBuilder(job, true).build()));

            String responseToOK = clientServerConnection.sendMessage("OK");
            if (!responseToOK.trim().equals(".")) {
                throw new Error("Unexpected ACK response from ds-sim server: " + responseToOK);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public int getEstimatedWaitTime() {
        try {
            String command = "EJWT " + this.serverType + " " + this.serverID;
            String responseToQuery = clientServerConnection.sendMessage(command);
            int estJobWaitTimeInQueue = Integer.parseInt(responseToQuery);
            return estJobWaitTimeInQueue;
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return 0;
        }
    }

    // can potentially be removed
    public boolean canHandleJobImmediately(Job job) {
        boolean hasEnoughCores = this.core > job.getCoresRequired();
        boolean hasEnoughMemory = this.memory > job.getMemoryRequired();
        boolean hasEnoughDisk = this.disk > job.getDiskRequired();
        return hasEnoughCores && hasEnoughMemory && hasEnoughDisk;
    }

    public void displayJobList() {
        System.out.println("======= Displaying Job List ========");
        for (Job job : this.jobList) {
            job.display();
        }
        System.out.println("======= Complete ========");
    }

    public void display() {
        System.out.println("==============");
        System.out.println("Server Type: " + serverType);
        System.out.println("Server ID: " + serverID);
        System.out.println("Server State: " + state);
        System.out.println("Server current start time: " + currentStartTime);
        System.out.println("Server Core: " + core);
        System.out.println("Server Memory: " + memory);
        System.out.println("Server Disk: " + disk);
        System.out.println("Number of waiting jobs: " + numWaitingJobs);
        System.out.println("Number of running jobs: " + numRunningJobs);
        System.out.println("==============");
    }
}
