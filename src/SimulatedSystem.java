import java.util.ArrayList;

public class SimulatedSystem {
    private ConfigDataLoader configDataLoader = ConfigDataLoader.getInstance();
    private ClientServerConnection clientServerConnection = ClientServerConnection.getInstance();
    
    private ArrayList<SimulatedServer> serverStore = new ArrayList<SimulatedServer>();
    private String largestServerType;

    public ArrayList<SimulatedServer> queryDSSim(String query) {
        String responseToQuery = clientServerConnection.sendMessage(query);
        System.out.println(responseToQuery);

        String[] queryIndicativeInformation = responseToQuery.split(" ");
        int numberOfServers = Integer.parseInt(queryIndicativeInformation[1]);
        int lengthPerRecord = Integer.parseInt(queryIndicativeInformation[2]);

        // adding some buffer to record length
        int adjustedRecordLength = lengthPerRecord + Integer.parseInt(configDataLoader.get("buffer_for_record_length"));
        byte[] buffer = new byte[numberOfServers * adjustedRecordLength];

        try {
            String responseToOK1 = clientServerConnection.sendMessage("OK", buffer);
            
            String responseToOK2 = clientServerConnection.sendMessage("OK");
            if (!responseToOK2.trim().equals(".")) {
                throw new Error("Unexpected ACK response from ds-sim server: " + responseToOK2);
            }

            ArrayList<SimulatedServer> list = new ArrayList<SimulatedServer>();
            String[] serversInformation = responseToOK1.split("\n");
            for (String server: serversInformation) list.add(new SimulatedServer(server));

            return list;
        } catch (Exception exc) {
            System.out.println("exception: " + exc);
            return null;
        }
    }

    // Sets the internally stored serverStore to the list of servers passed in
    public void setServerStore(ArrayList<SimulatedServer> servers) {
        this.serverStore = servers;
        this.largestServerType = this.getLargestServer().getServerType();
    }

    public void updateServersInStore(ArrayList<SimulatedServer> servers) {
        // System.out.println("store:");
        // for (SimulatedServer s: this.serverStore) {
        //     System.out.println("Server: " + s.getServerType() + " " + s.getID());
        // }

        for (SimulatedServer server: servers) {
            // if the server exists in the store, update it in place. Otherwise, add it to the store
            boolean alreadyInCache = false;

            for (SimulatedServer s: this.serverStore) {
                if (s.getID() == server.getID() && (s.getServerType().equals(server.getServerType()))) { // we already know its in cache, no point in searching the rest of the cache
                    alreadyInCache = true;

                    int index = this.serverStore.indexOf(s);
                    server.setServerCores(s.getServerCores());
                    this.serverStore.set(index, server);
                }
            }

            if (alreadyInCache) {
                continue;
            }

            this.serverStore.add(server);
        }
        // System.out.println("==== updated server store. printing... =====");
        // printStore();
        // System.out.println("==== done =====");
    }

    public String getTypeOfLargestServer() {
        return this.largestServerType;
    }

    public ArrayList<SimulatedServer> getServerStore() {
        return this.serverStore;
    }

    public SimulatedServer getLargestServer() {
        SimulatedServer largest = this.serverStore.get(0);
        for (SimulatedServer simulatedServer: this.serverStore) {
            if (simulatedServer.getNumberOfCores() > largest.getNumberOfCores()) {
                largest = simulatedServer;
            }
        }
        return largest;
    }

    public SimulatedServer getSmallestServer(ArrayList<SimulatedServer> searchList) {
        // loop over the search list
        // grab the stored version of each server and do comparison
        SimulatedServer smallest = searchList.get(0);
        for (SimulatedServer simulatedServer: searchList) {
            if (simulatedServer.getNumberOfCores() < smallest.getNumberOfCores()) {
                smallest = simulatedServer;
            }
        }
        return smallest;
    }

    public SimulatedServer getBusiestLargestServer() {
        ArrayList<SimulatedServer> listOfServers = this.findServersByType(this.largestServerType);
        SimulatedServer busiest = listOfServers.get(0);
        for (SimulatedServer simulatedServer: listOfServers) {
            if (simulatedServer.getNumberOfCores() < busiest.getNumberOfCores()) {
                busiest = simulatedServer;
            }
        }
        return busiest;
    }

    public SimulatedServer findNextServerByType(String serverType, SimulatedServer current) {
        ArrayList<SimulatedServer> listOfServers = this.findServersByType(serverType);
        SimulatedServer nextServer = this.getNextServerById(listOfServers, current);
        return nextServer;
    }

    public ArrayList<SimulatedServer> findServersByType(String serverType) {
        ArrayList<SimulatedServer> list = new ArrayList<SimulatedServer>();

        for (SimulatedServer simulatedServer: this.serverStore) {
            if (simulatedServer.getServerType().equals(serverType)) list.add(simulatedServer);
        }
        return list;
    }

    public SimulatedServer getNextServerById(ArrayList<SimulatedServer> list, SimulatedServer current) {
        if (current == null) return list.get(0);
        for (SimulatedServer simulatedServer: list) {
            if (simulatedServer.getID() > current.getID()) return simulatedServer;
        }
        return list.get(0);
    }

    // should this be based by core count + memory + disk space required?
    public SimulatedServer getSoonestAvailableServerByCoreCount(int coresRequired, ArrayList<SimulatedServer> searchList) {
        // find the server that can soonest process a job with this many cores required.
        ArrayList<SimulatedServer> tempList = new ArrayList<SimulatedServer>(0);
        for (SimulatedServer server: searchList) {
            for (SimulatedServer simulatedServer: this.serverStore) {
                if (server.getID() == simulatedServer.getID() && (server.getServerType().equals(simulatedServer.getServerType()))) {
                    tempList.add(server);
                }
            }
        }

        SimulatedServer found = tempList.get(0);

        for (SimulatedServer simulatedServer: tempList) {
            if (simulatedServer.getWaitTime(coresRequired) < found.getWaitTime(coresRequired)) {
                found = simulatedServer;
            }
        }

        return found;
    }

    public SimulatedServer getServerById(String type, int id, ArrayList<SimulatedServer> storeToSearch) {
        for (SimulatedServer simulatedServer: storeToSearch) {
            if (simulatedServer.getID() == id && (simulatedServer.getServerType().equals(type.trim()))) {
                return simulatedServer;
            }
        }
        return null;
    }

    public void scheduleJob(SimulatedServer server, Job job) {
        // store that we've used this server in the usedServers list with a calculated serverCores etc.
        // if the server exists in the store, we update it 
        // if new to us, we add it to the store
        SimulatedServer foundServer = findInStoreByTypeAndId(server.getServerType(), server.getID());
        System.out.println("found this server to send job to:");
        foundServer.display();

        foundServer.scheduleJob(job.getID());
        foundServer.setCoresBeingUsed(job.getCoresRequired(), job.getEstimatedRunTime());

        System.out.println("after scheduling the job:");
        foundServer.display();

        for (SimulatedServer simulatedServer: this.serverStore) {
            if (simulatedServer.getID() == foundServer.getID() && (simulatedServer.getServerType().equals(foundServer.getServerType()))) {
                int index = this.serverStore.indexOf(simulatedServer);
                foundServer.setServerCores(simulatedServer.getServerCores());
                this.serverStore.set(index, server);
                break;
            }
        }
        return;
    }

    public SimulatedServer findInStoreByTypeAndId(String type, int id) {
        for (SimulatedServer simulatedServer: this.serverStore) {
            if (simulatedServer.getID() == id && (simulatedServer.getServerType().equals(type.trim()))) {
                return simulatedServer;
            }
        }
        return null;
    }

    public void pushCurrentJob() {
        try {
            String command = "PSHJ";
            String resposeToPushJob = clientServerConnection.sendMessage(command);
            if (!resposeToPushJob.trim().equals("OK")) {
                throw new Error("Unexpected push job response from ds-sim server: " + resposeToPushJob);
            }
        } catch (Exception e) {
            System.out.println("IOException: " + e);
        }
    }

    public void printStore() {
        for (SimulatedServer simulatedServer: this.serverStore) {
            simulatedServer.display();
        }
    }
}
