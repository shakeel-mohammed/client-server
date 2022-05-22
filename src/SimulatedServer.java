import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    private ArrayList<ServerCore> serverCores = new ArrayList<ServerCore>();

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

        // initialize the server cores
        for (int i = 0; i < this.core; i++) {
            this.serverCores.add(new ServerCore(i));
        }
    }

    public int getID() {
        return this.serverID;
    }

    public int getNumberOfCores() {
        return this.core;
    }

    public ArrayList<ServerCore> getServerCores() {
        return this.serverCores;
    }

    public void setServerCores(ArrayList<ServerCore> cores) {
        this.serverCores = cores;
        return;
    }

    public String getServerType() {
        return this.serverType.trim();
    }

    public boolean isBooting() {
        if (this.state.trim().equals("booting")) return true;
        return false;
    }

    // here is where we set how many cores and disk space has been taken up
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

    public void queryJobList() {
        try {
            String command = "LSTJ " + this.serverType + " " + this.serverID;
            String responseToQuery = clientServerConnection.sendMessage(command);

            String[] jobsIndicativeInformation = responseToQuery.split(" ");
            int numberOfJobs = Integer.parseInt(jobsIndicativeInformation[1]);
            int lengthPerRecord = Integer.parseInt(jobsIndicativeInformation[2]);

            // adding some buffer to record length
            int adjustedRecordLength = lengthPerRecord + Integer.parseInt(configDataLoader.get("buffer_for_record_length"));
            byte[] buffer = new byte[numberOfJobs * adjustedRecordLength];

            String jobListString = clientServerConnection.sendMessage("OK", buffer);
            String[] jobs = jobListString.split("\n");
            
            for (String job: jobs) this.jobList.add(new Job(new JobInformationBuilder(job, true).build()));

            String responseToOK = clientServerConnection.sendMessage("OK");
            if (!responseToOK.trim().equals(".")) {
                throw new Error("Unexpected ACK response from ds-sim server: " + responseToOK);
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
    }

    public double getWaitTime(int coresRequired) {
        // eg. coresRequired = 3
        // we pull the 3 cores that will be free the soonest
        // if find out when all of these cores will be free at the latest
        // e.g core1 = 10s, core2 = 12s, core3 = 8s
        // return 12 seconds. 

        ArrayList<ServerCore> allServerCores = this.serverCores;
        ArrayList<ServerCore> serverCoresToBeUsed = new ArrayList<ServerCore>(0);

        // sort the serverCores based on timeUntilAvailable. 10, 12, 8 -> 8, 10, 12 
        Collections.sort(allServerCores, new Comparator<ServerCore>() {
            @Override
            public int compare(ServerCore lhs, ServerCore rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                if (lhs.timeUntilAvailable() > rhs.timeUntilAvailable()) {
                    return -1;
                }
                if (lhs.timeUntilAvailable() < rhs.timeUntilAvailable()) {
                    return 1;
                }
                return 0;
            }
        });

        // pull out the cores that would be getting used to run this job
        for (int i = 0; i < coresRequired; i++) {
            serverCoresToBeUsed.add(allServerCores.get(i));
        }

        return serverCoresToBeUsed.get(serverCoresToBeUsed.size() -1).timeUntilAvailable();
    }

    public void setCoresBeingUsed(int coresBeingUsed, double duration) {
        // find the cores that will become free soonest
        // reserve those cores until the end of the duration
        ArrayList<ServerCore> allServerCores = this.serverCores;

        // sort the serverCores based on timeUntilAvailable. 10, 12, 8 -> 8, 10, 12 
        Collections.sort(allServerCores, new Comparator<ServerCore>() {
            @Override
            public int compare(ServerCore lhs, ServerCore rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                if (lhs.timeUntilAvailable() > rhs.timeUntilAvailable()) {
                    return -1;
                }
                if (lhs.timeUntilAvailable() < rhs.timeUntilAvailable()) {
                    return 1;
                }
                return 0;
            }
        });

        // pull out the cores that would be getting used to run this job
        for (int i = 1; i < coresBeingUsed; i++) {
            ServerCore coreToBeUsed = allServerCores.get(i);
            int index = allServerCores.indexOf(coreToBeUsed);
            coreToBeUsed.reserveForDuration(duration);
            allServerCores.set(index, coreToBeUsed);
        }
        return;
    }

    public boolean canHandleJobImmediately(Job job) {
        boolean hasEnoughCores = this.core > job.getCoresRequired();
        boolean hasEnoughMemory = this.memory > job.getMemoryRequired();
        boolean hasEnoughDisk = this.disk > job.getDiskRequired();
        return hasEnoughCores && hasEnoughMemory && hasEnoughDisk;
    }

    public void displayJobList() {
        System.out.println("======= Displaying Job List ========");
        for(Job job : this.jobList) {
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
