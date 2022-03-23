
public class JobN {
    private Double submitTime;
    private int jobID;
    private Double estimatedRunTime;
    private int core;
    private int memory;
    private int disk;

    JobN(String strigifiedJobNInformation) {
        // break the string by the space between each attribute
        String[] jobInformation = strigifiedJobNInformation.split(" ");
        
        // the location of each attribute is pre-determined. We can use this to correctly assign state variables.
        this.submitTime = Double.parseDouble(jobInformation[1]);
        this.jobID = Integer.parseInt(jobInformation[2]);
        this.estimatedRunTime = Double.parseDouble(jobInformation[3]);
        this.core = Integer.parseInt(jobInformation[4]);
        this.memory = Integer.parseInt(jobInformation[5]);
        this.disk = Integer.parseInt(jobInformation[6]);
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

    public void display() {
        System.out.println("================");
        System.out.println("Submit Time: " + submitTime);
        System.out.println("Job ID: " + jobID);
        System.out.println("Est Run Time: " + estimatedRunTime);
        System.out.println("Job Cores Required: " + core);
        System.out.println("Job Memory Required: " + memory);
        System.out.println("Job Disk Required: " + disk);
        System.out.println("================");
    }
}
