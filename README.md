## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).


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