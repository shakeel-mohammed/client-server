
public class JobInformationBuilder {
  private String jobState;
  private Double submitTime;
  private int jobID;
  private Double startTime;
  private Double endTime;
  private Double estimatedRunTime;
  private int core;
  private int memory;
  private int disk;
  private String serverType;
  private int serverID;

  JobInformationBuilder(String strigifiedJobNInformation, boolean isFromListJobsCommand) {
    // break the string by the space between each attribute
    String[] jobInformation = strigifiedJobNInformation.split(" ");

    // the format of the attributes changes between different responses. eg. REDY vs
    // LSTJ
    if (isFromListJobsCommand) { // assume attributes in position as per LSTJ response
      parseBasedOnListJobsResponse(jobInformation);
    } else { // assume attributes in position as per REDY response
      String jobState = jobInformation[0];
      switch (jobState) {
        case "JCPL":
          parseBasedOnCompletedJobString(jobInformation);
          break;
        case "JOBN":
        default:
          parseBasedOnNewJobString(jobInformation);
      }
    }
  }

  // JOBN 156 5 8 3 2700 2600
  private void parseBasedOnNewJobString(String[] jobInfo) {
    this.jobState = parseJobState(jobInfo[0]);
    this.submitTime = Double.parseDouble(jobInfo[1]);
    this.jobID = Integer.parseInt(jobInfo[2]);
    this.estimatedRunTime = Double.parseDouble(jobInfo[3]);
    this.core = Integer.parseInt(jobInfo[4]);
    this.memory = Integer.parseInt(jobInfo[5]);
    this.disk = Integer.parseInt(jobInfo[6]);
  }

  // JCPL endTime jobID serverType serverID
  private void parseBasedOnCompletedJobString(String[] jobInfo) {
    this.jobState = parseJobState(jobInfo[0]);
    this.endTime = Double.parseDouble(jobInfo[1]);
    this.jobID = Integer.parseInt(jobInfo[2]);
    this.serverType = jobInfo[3];
    this.serverID = Integer.parseInt(jobInfo[4]);
  }

  // jobID jobState submitTime startTime estRunTime core memory disk
  private void parseBasedOnListJobsResponse(String[] jobInfo) {
    this.jobID = Integer.parseInt(jobInfo[0]);
    this.jobState = parseJobState(jobInfo[1]);
    this.submitTime = Double.parseDouble(jobInfo[2]);
    this.startTime = Double.parseDouble(jobInfo[3]);
    this.estimatedRunTime = Double.parseDouble(jobInfo[4]);
    this.core = Integer.parseInt(jobInfo[5]);
    this.memory = Integer.parseInt(jobInfo[6]);
    this.disk = Integer.parseInt(jobInfo[7]);
  }

  private String parseJobState(String typeString) {
    switch (typeString) {
      case "JOBN":
      case "0":
        return "submitted";
      case "1":
        return "waiting";
      case "2":
        return "running";
      case "3":
        return "suspended";
      case "JCPL":
      case "4":
        return "completed";
      case "JOBP":
      case "5":
        return "re-submission";
      case "6":
        return "failed";
      case "7":
        return "killed";
      default:
        return null;
    }
  }

  public JobInformation build() {
    JobInformation jobInfo = new JobInformation();
    jobInfo.jobID = this.jobID;
    jobInfo.jobState = this.jobState;
    jobInfo.submitTime = this.submitTime;
    jobInfo.startTime = this.startTime;
    jobInfo.endTime = this.endTime;
    jobInfo.estimatedRunTime = this.estimatedRunTime;
    jobInfo.core = this.core;
    jobInfo.memory = this.memory;
    jobInfo.disk = this.disk;
    jobInfo.serverType = this.serverType;
    jobInfo.serverID = this.serverID;

    return jobInfo;
  }
}
