5 PAGES MAX
1. Introduction (1/2 page)
    - What is this project? 
        - briefly explain distributed systems, breifly explain ds-sim, explain the goal of this project
2. System Overview (1/2 page)
    - high-level description of the system, include a diagram to show overall flow
    - be sure to talk about how ds-sim works
    - talk about the protocol
3. Design (1 page)
    - include design philosophy (i wanted a design that was easily controlled and extended to support future goals)
    - talk about the alogrithm (and how the client can be extended to support more algorithms)
    - considerations
    - constraints
    - functionality of each component on the client-side
    - talk about each class
4. Implementation (2 pages)
    - technologies, libraries, data structures, (invariants?) used
    - talk about HOW YOU USED the components above
5. References 
    - project git repository link
    - sources
    - etc
    - make sure we use bibtex citations
        - make bibtex file
        - cite in main file
    e.g the java client employs comparable interface \cite []....

1. What is this project?

This report presented is to describe the overview, design, and implementation of a scheduling system that implements the Largest Round Robin algorithm whilst distributing jobs to servers in a simulated distributed system (provided by the MQ University). We want to compare with a reference implementation (also provided by MQ University).

At it's core, a distributed system is a system where it's various components are spread across a network. Think of cloud computing where thousands of computers can be used to handle complex tasks by breaking down jobs and handling them simulantously. This is a distributed system.

In a distributed system, the load is distributed across multiple machines to achieve results that are much faster and reliable than having one server that handles all requests. Each distributed system requires a mechanism to orchestrate and handle each request. This component is responsible for making scheduling decisions which may vary depending on the desired behaviour the algorithm.

What is the goal?
The goal for this project is to have a client (described below) which connects to the ds-sim server (described below), receives jobs to be scheduled, and makes scheduling decisions based on LLR. Once the ds-sim server has run out of jobs to be process, the client should gracefully disconnect from the ds-sim server.