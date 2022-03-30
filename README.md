## Getting Started

## Findings
As per LRR, if there are two server with the same number of cores, the client must schedule them in a round-robin manner. eg. 
- 4 jobs come in with the varying core requirements.
- we have 3 servers available, all with the same number of cores (joon 1, joon 2, joon 3) (server-type server-id) (lets say 16 cores)
- a new job comes in (3 cores required).
    - client schedules the job with joon 1. (at this point, joon 1 has 13 available cores)
- another new job comes in (1 core required).
    - client schedules the job with joon 2. (at this point, joon 2 has 15 available cores)
- another new job comes in (2 cores required).
    - client schedules the job with joon 3. (at this point, joon 3 has 14 available cores)
- another new job comes in (2 cores).
    - client schedules the job with joon 2 (because it has the highest number of cores available). (at this point, joon 2 has 13 available cores)

From oberserving the logs, we find that the provided client schedules jobs in this manner:
- 4 jobs come in with the varying core requirements.
- we have 3 servers available, all with the same number of cores (joon 1, joon 2, joon 3) (server-type server-id) (lets say 16 cores)
- a new job comes in (3 cores required).
    - client schedules the job with joon 1. (at this point, joon 1 has 13 available cores)
- another new job comes in (1 core required).
    - client schedules the job with joon 2. (at this point, joon 2 has 15 available cores)
- another new job comes in (2 cores required).
    - client schedules the job with joon 3. (at this point, joon 3 has 14 available cores)
- another new job comes in (2 cores).
    - client schedules the job with joon 1 (Because its all of the other joon servers have already had a job scheduled in them? so this is the next because we're starting a new round? this is an issue, because we're no longer looking at the number of cores, rather the type of server). (at this point, joon 1 has 11 available cores)

As we can see, the provided client does not seem to continue scheduling the job based on the core count of the server, rather the serverType (being joon). the cycle was as follows
1. joon 1
2. joon 2
3. joon 3
4. joon 1

if we're scheduling based on server core count
1. joon 1
2. joon 2
3. joon 3
4. joon 2 (because joon 2 has 15 available cores)

So really, to achieve LRR, we need to handle as follows:
1. first job comes in - done
2. query the list of servers capable of handling the job (the largest servers are bound to come up) - done
3. we find the serverType that has the highest cores (largest server type) - done
4. we store that serverType in the SimulatedSystem - done
5. we schedule the job to the first server of that type - done
6. we store the ID of that server - done
7. x job comes in - done
8. query the list of servers capable of handling the job (the largest servers are bound to come up) - done
9. use a findServersByServerType function to first list down based on stored serverType - done
10. use a getNextServer function to get the next server in the list based the ID of the stored server (if storedServer ID is -1, we just get the first server) - done
11. update the storedServer to the result of the getNextServer function - done
12. schedule the job to that server - done
13. repeat process - done

1. find the first server that we have of that type
2. schedule the job to that server
3. we store the 
3. when another job comes in, regardless of how many cores it requires, when we ask for compatible servers, it will return all servers of the largest type again. We again find the 

## Test Results
### Sample config 2
# -------------------------------------------------------------------------------------
# 0 tiny servers used with a utilisation of 0.00 at the cost of $0.00
# 0 small servers used with a utilisation of 0.00 at the cost of $0.00
# 0 medium servers used with a utilisation of 0.00 at the cost of $0.00
# 0 large servers used with a utilisation of 0.00 at the cost of $0.00
# 10 xlarge servers used with a utilisation of 98.88 at the cost of $753.73
# ==================================== [ Summary ] ====================================
# actual simulation end time: 268869, #jobs: 500 (failed 0 times)
# total #servers used: 10, avg util: 98.88% (ef. usage: 98.97%), total cost: $753.73
# avg waiting time: 33232, avg exec time: 6322, avg turnaround time: 39554

This is in-line with provided ds-client implementation


### Sample config 1
# -------------------------------------------------------------------------------------
# 0 juju servers used with a utilisation of 0.00 at the cost of $0.00
# 0 joon servers used with a utilisation of 0.00 at the cost of $0.00
# 1 super-silk servers used with a utilisation of 100.00 at the cost of $0.43
# ==================================== [ Summary ] ====================================
# actual simulation end time: 2045, #jobs: 10 (failed 0 times)
# total #servers used: 1, avg util: 100.00% (ef. usage: 100.00%), total cost: $0.43
# avg waiting time: 49, avg exec time: 728, avg turnaround time: 777

This is in-line with provided ds-client implementation


## To compile
run `bash scripts/compile.sh`

## To run
cd into `compiled` directory
run `java Client`