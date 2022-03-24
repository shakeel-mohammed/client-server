
public class Job {
    private String jobState;
    private Double startTime;
    private Double submitTime;
    private int jobID;
    private Double estimatedRunTime;
    private int core;
    private int memory;
    private int disk;

    Job(JobInformation info) {
        this.jobState = info.jobState;
        this.jobID = info.jobID;
        this.submitTime = info.submitTime;
        this.startTime = info.startTime;
        this.estimatedRunTime = info.estimatedRunTime;
        this.core = info.core;
        this.memory = info.memory;
        this.disk = info.disk;
    }

    public int getID() {
        return this.jobID;
    }

    public int getCoresRequired() {
        return this.core;
    }

    public int getMemoryRequired() {
        return this.memory;
    }

    public int getDiskRequired() {
        return this.disk;
    }

    public boolean isComplete() {
        return this.jobState.equals("completed");
    }

    public void display() {
        System.out.println("================");
        System.out.println("Job State: " + jobState);
        System.out.println("Submit Time: " + submitTime);
        System.out.println("Start Time: " + startTime);
        System.out.println("Job ID: " + jobID);
        System.out.println("Est Run Time: " + estimatedRunTime);
        System.out.println("Job Cores Required: " + core);
        System.out.println("Job Memory Required: " + memory);
        System.out.println("Job Disk Required: " + disk);
        System.out.println("================");
    }
}
