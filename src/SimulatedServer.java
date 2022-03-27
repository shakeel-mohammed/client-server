
public class SimulatedServer {
    private ClientServerConnection connection = ClientServerConnection.getInstance();

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

    SimulatedServer(String strigifiedServerInformation) {
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

    public int getID() {
        return this.serverID;
    }

    public int getNumberOfCores() {
        return this.core;
    }

    public String getServerType() {
        return this.serverType.trim();
    }

    public void scheduleJob(int jobID) {
        try {
            String command = "SCHD " + jobID + " " + this.serverType + " " + this.serverID;
            String resposeToJobSchedule = connection.sendMessage(command);
            if (!resposeToJobSchedule.trim().equals("OK")) {
                throw new Error("Unexpected job scheduling response from ds-sim server: " + resposeToJobSchedule);
            }
        } catch (Exception e) {
            System.out.println("IOException: " + e);
        }
    }

    public void queryJobList() {
        try {
            String command = "LSTJ " + this.serverType + " " + this.serverID;
            String responseToQuery = connection.sendMessage(command);

            String[] jobsIndicativeInformation = responseToQuery.split(" ");
            int numberOfJobs = Integer.parseInt(jobsIndicativeInformation[1]);
            int lengthPerRecord = Integer.parseInt(jobsIndicativeInformation[2]);
            int adjustedRecordLength = lengthPerRecord + 3; // adding some buffer
            byte[] buffer = new byte[numberOfJobs * adjustedRecordLength];

            this.jobList = new Job[numberOfJobs];

            String jobListString = connection.sendMessage("OK", buffer);
            String[] jobs = jobListString.split("\n");

            for (int i = 0; i < this.jobList.length; i++) {
                Job job = new Job(new JobInformationBuilder(jobs[i], true).build());
                this.jobList[i] = job;
            }

            String responseToOK = connection.sendMessage("OK");
            if (!responseToOK.trim().equals(".")) {
                throw new Error("Unexpected ACK response from ds-sim server: " + responseToOK);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
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
