// import java.util.ArrayList;

// public class Orchestrator {
//     private ClientServerConnection clientServerConnection = ClientServerConnection.getInstance();
//     private SimulatedSystem simulatedSystem = new SimulatedSystem();
//     private String largestServerType;
//     private SimulatedServer mostRecentlyUsedServer = null;
//     private String algorithm;

//     public Orchestrator(String algorithm) {
//         this.algorithm = algorithm.toLowerCase().trim();
//     }

//     public void run() {
//         switch (this.algorithm) {
//             case "lrr":
//                 runWithLargestRoundRobin();
//                 break;
//             case "fc":
//                 runWithFirstCapable();
//                 break;
//             case "opt-tat":
//                 runWithOptForTaTv2();
//                 break;
//             default:
//                 throw new Error("algorithm not set up");
//         }
//     }

//     private void runWithLargestRoundRobin() {
//         Boolean shouldDisplayJobList = false;
//         String event;
//         while (!(event = clientServerConnection.sendMessage("REDY")).contains("NONE")) { // we're in trouble if any other response contains this substring
//             if (event.trim().equals("ERR")) {
//                 System.out.println("encountered an error: " + event);
//                 break;
//             }
//             System.out.println("new response to REDY: " + event);
//             Job job = new Job(new JobInformationBuilder(event, false).build());

//             if (!job.isComplete()) {
//                 String query = job.buildQueryForCapableServer();
//                 ArrayList<SimulatedServer> realtimeServerInformation = simulatedSystem.queryDSSim(query);
//                 simulatedSystem.setServerStore(realtimeServerInformation);


//                 this.largestServerType = simulatedSystem.getTypeOfLargestServer();
    
//                 SimulatedServer serverToScheduleJob = simulatedSystem.findNextServerByType(this.largestServerType, mostRecentlyUsedServer);
                
//                 System.out.println("found available server:");
//                 serverToScheduleJob.display();

//                 serverToScheduleJob.scheduleJob(job.getID());
//                 this.mostRecentlyUsedServer = serverToScheduleJob;

//                 if (shouldDisplayJobList) {
//                     System.out.println("querying server job list...");
//                     serverToScheduleJob.queryJobList();
//                     serverToScheduleJob.displayJobList();
//                 }
//             }
//         }
//     }

//     private void runWithFirstCapable() {
//         Boolean shouldDisplayJobList = false;
//         String event;
//         while (!(event = clientServerConnection.sendMessage("REDY")).contains("NONE")) { // we're in trouble if any other response contains this substring
//             if (event.trim().equals("ERR")) {
//                 System.out.println("encountered an error: " + event);
//                 break;
//             }
//             System.out.println("new response to REDY: " + event);
//             Job job = new Job(new JobInformationBuilder(event, false).build());

//             if (!job.isComplete()) {
//                 String query = job.buildQueryForCapableServer();
//                 simulatedSystem.queryDSSim(query);
    
//                 SimulatedServer serverToScheduleJob = simulatedSystem.getServerStore().get(0);

//                 if (serverToScheduleJob == null) {
//                     throw new Error("No server capable of handling job");
//                 }
                
//                 System.out.println("found available server:");
//                 serverToScheduleJob.display();

//                 serverToScheduleJob.scheduleJob(job.getID());
//                 this.mostRecentlyUsedServer = serverToScheduleJob;

//                 if (shouldDisplayJobList) {
//                     System.out.println("querying server job list...");
//                     serverToScheduleJob.queryJobList();
//                     serverToScheduleJob.displayJobList();
//                 }
//             }
//         }
//     }

//     private void runWithOptForTaTv1() {
//         Boolean shouldDisplayJobList = false;
//         String event;
//         // list all servers
//         // count up how many cores available for the cheapest server type. eg. 1 mini server at 4 cores.
//         // query jobs until we've reached that count. eg 2 jobs (2 cores each)
//         // if the number doesn't exactly fit, query until we find a job that fits.
//         // if we've queried enough for a whole new server, 
//         while (!(event = clientServerConnection.sendMessage("REDY")).contains("NONE")) { // we're in trouble if any other response contains this substring
//             if (event.trim().equals("ERR")) {
//                 System.out.println("encountered an error: " + event);
//                 break;
//             }
//             System.out.println("new response to REDY: " + event);
//             Job job = new Job(new JobInformationBuilder(event, false).build());

//             if (!job.isComplete()) {
//                 String query = job.buildQueryForCapableServer();
//                 simulatedSystem.queryDSSim(query);

//                 // find the server of the smallest type.
//                 // find that has free cores to handle the job right now. 
//                 // if no cores available, do cost analysis of using bigger server.
//                 // if not the smallest type, do cost analysis.
//                 // if is the smallest type, schedule the job?
//                 SimulatedServer serverToScheduleJob = simulatedSystem.getServerStore().get(0);

//                 if (serverToScheduleJob == null) {
//                     throw new Error("No server capable of handling job");
//                 }
                
//                 System.out.println("found available server:");
//                 serverToScheduleJob.display();

//                 serverToScheduleJob.scheduleJob(job.getID());
//                 this.mostRecentlyUsedServer = serverToScheduleJob;

//                 if (shouldDisplayJobList) {
//                     System.out.println("querying server job list...");
//                     serverToScheduleJob.queryJobList();
//                     serverToScheduleJob.displayJobList();
//                 }
//             }
//         }
//     }

//     private void runWithOptForTaTv2() {
//         String event;
//         // ask the server - when will you be able to take this job? (depends on number of cores required)
//         // when requiring spinning up another server, we check how long that server will take to boot
//         // if the boot-time is longer than the remaining time on an already running server's cores, we queue the job to be run later
//         // using a runAt field (JobQueue)
//         // at each new iteration, we check this queue for any jobs that should be run right now
//         while (!(event = clientServerConnection.sendMessage("REDY")).contains("NONE")) { // we're in trouble if any other response contains this substring
//             if (event.trim().equals("ERR")) {
//                 System.out.println("encountered an error: " + event);
//                 break;
//             }
//             System.out.println("new response to REDY: " + event);
//             Job job = new Job(new JobInformationBuilder(event, false).build());

//             if (!job.isComplete()) {
//                 String query = job.buildQueryForCapableServer();
//                 ArrayList<SimulatedServer> realtimeServerInformation = simulatedSystem.queryDSSim(query);
//                 SimulatedServer targetServer;

//                 // SimulatedServer smallestServerActiveServer = simulatedSystem.getSmallestServer(realtimeServerInformation, "active");

//                 // if (smallestServerActiveServer == null) {
//                 //
//                 // }

//                 // for now
//                 // targetServer = smallestServerActiveServer;

//                 // if cheapest is not running, check how long it'll take to boot up

//                 // SimulatedServer cheapestRunningServer = simulatedSystem.getSmallestServer("active");
//                 // double cheapestRunningCanRunAt = cheapestServer.getWaitTimeBasedOnCoresRequired(job.getCoresRequired());

//                 // updates on all servers and stuff, including the one we're going to use
//                 // updates the servers in store with core, disk, etc from realtime response. Will add if doesn't exist, will persist serverCores.
//                 simulatedSystem.updateServersInStore(realtimeServerInformation);

//                 // for now
//                 targetServer = simulatedSystem.getSoonestAvailableServerByCoreCount(job.getCoresRequired(), realtimeServerInformation);
                
//                 System.out.println("found available server:");
//                 targetServer.display();

//                 // updates just the one we've just used 
//                 simulatedSystem.scheduleJob(targetServer, job);
//             }
//         }
//     }

// private void runWithLargestRoundRobinOpt() {
//     String event;
//     while (!(event = clientServerConnection.sendMessage("REDY")).contains("NONE")) { // we're in trouble if any other response contains this substring
//         if (event.trim().equals("ERR")) {
//             System.out.println("encountered an error: " + event);
//             break;
//         }
//         System.out.println("new response to REDY: " + event);
//         Job job = new Job(new JobInformationBuilder(event, false).build());

//         int coreLimit = 0;
//         String largestServerType = "";

//         if (!job.isComplete()) {
//             String query = job.buildQueryForCapableServer();
//             ArrayList<SimulatedServer> realtimeServerInformation = simulatedSystem.queryDSSim(query);
//             simulatedSystem.setServerStore(realtimeServerInformation);

//             ArrayList<Job> jobStore = new ArrayList<Job>(0);
//             boolean hasMoreJobsToProcess = true;

//             // this splits the jobs up and tries to send to smaller servers
//             jobStore.add(job);

//             SimulatedServer largest = simulatedSystem.getLargestServer();

//             // these are set once and then persisted
//             if (coreLimit == 0) coreLimit = largest.getNumberOfCores();
//             if (largestServerType.equals("")) largestServerType = largest.getServerType();

//             SimulatedServer target = largest;

//             // Not enough cores available, this job will be queued
//             // eg. largest is supersilk, with 4 free cores but job needs 6
//             // checking the booting stuff is really adding to the TAT. 9739.33 -> 11021.72
//             if (target.getNumberOfCores() < job.getCoresRequired() || (target.isBooting() && (mostRecentlyUsedServer.getID() != target.getID() && !mostRecentlyUsedServer.getServerType().equals(target.getServerType())))) {
                
//                 // this actually doesn't "step-down" for the next biggest server type if all largest servers are busy...
//                 target = simulatedSystem.findNextServerByType(largestServerType, mostRecentlyUsedServer);

//                 // if this job cannot fit anywhere. ie. no largest servers are able to handle the job right now
//                 // pushJ for next run.

//                 // if this job has already been pushed, we either migrate a job from the target server,
//                 // or we simply queue the job to the server with the lowest estimated wait time.
//                 // for now, we'll just queue it.

//                 // maybe we split them up if we know there won't be any more jobs?
//                 // It is cheaper and quicker that way (smaller servers spin up faster)

//                 // query for more jobs we have atleast enough to fill the coreLimit
//                 // or until there are no more jobs
//                 // e.g coreLimit = 64
//                 // currentJob = 4, jobStore = 4
//                 // newJob = 2, jobStore = 6
//                 // newJob = 8, jobStore = 14
//                 // newJob = 32, jobStore = 46
//                 // newJob = 24, jobStore = 70 ??
//                 // newJob = NONE, loop ends

//                 // this doesn't take into account that the largest server we would send job to could already have jobs process
//                 // so the coreLimit would be lower.. we could set the core limit dynamically
//                 // but then we'd also need to be sure to send to that server later. eg. get largest server based on realtime server info
//                 // String jobEvent = "";
//                 // while(countJobCoresInStore(jobStore) < coreLimit && !(jobEvent = clientServerConnection.sendMessage("REDY")).contains("NONE")) {
//                 //     if (jobEvent.trim().equals("ERR")) {
//                 //         System.out.println("encountered an error: " + jobEvent);
//                 //         break;
//                 //     }
//                 //     System.out.println("new response to REDY for filling up job bucket: " + jobEvent);
//                 //     Job newJob = new Job(new JobInformationBuilder(jobEvent, false).build());

//                 //     if (!newJob.isComplete()) {
//                 //         jobStore.add(newJob);
//                 //     }
//                 // }

//                 // if (jobEvent.equals("NONE")) {
//                 //     // we ran out of jobs before we hit the limit. Lets split these up and send them to smaller servers.
//                 //     hasMoreJobsToProcess = false;
//                 // } else {
//                 //     // we still have jobs to process. 
//                 //     hasMoreJobsToProcess = true;
//                 // }
//             }

//             // // by the time we get here, we've already either run out of jobs or we have more than one largest server can handle
//             // // size will always be at least one. 
//             // if (jobStore.size() > 0) {
//             //     // e.g.
//             //     // coreLimit: 64
//             //     // jobStore = [{ j1: 2cores }, {j2: 30cores }, {j3: 32cores}];
//             //     if (hasMoreJobsToProcess) {
//             //         // we send the jobs to the next largest server. (already been added to the store)
//             //         // loop over the job store and send them all of the largest server.
//             //         for (Job j: jobStore) {
//             //             // find the largest server that can handle these jobs and schedule
//             //             target.scheduleJob(j.getID());
//             //             this.mostRecentlyUsedServer = target;
//             //         }
//             //     } else {
//             //         // we do not have any more jobs to process, we find the smallest server that can handle these jobs and schedule
//             //         for (Job j: jobStore) {
//             //             // find the smallest server that can handle these jobs and schedule
//             //             // need to do gets capable for each of these jobs
//             //             String queryDsSim = j.buildQueryForCapableServer();
//             //             ArrayList<SimulatedServer> realtimeServerInformationForJob = simulatedSystem.queryDSSim(queryDsSim);
//             //             target = simulatedSystem.getSmallestServer(realtimeServerInformationForJob);
//             //             target.scheduleJob(j.getID());
//             //             this.mostRecentlyUsedServer = target;
//             //         }
//             //     }
//             // }

//             // for (Job j: jobStore) {
//             //     System.out.println("SCHEDULING JOB ===");
//             //     j.display();

//             //     // find the largest server that can handle these jobs and schedule
//             //     target.scheduleJob(j.getID());
//             //     this.mostRecentlyUsedServer = target;
//             // }
            
//             System.out.println("found available server:");
//             target.display();

//             target.scheduleJob(job.getID());
//             this.mostRecentlyUsedServer = target;
//         }
//     }
// }

// }
