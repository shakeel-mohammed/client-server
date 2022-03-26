
public class SimulatedSystem {
    private SimulatedServer[] serverStore;
    private String largestServerType;
    private SimulatedServer mostRecentlyUsedServer = null;

    public void refreshServerStore(int numServers, String strigifiedServers) {
        this.serverStore = new SimulatedServer[numServers];
        String[] serversInformation = strigifiedServers.split("\n");

        for (int i = 0; i < this.serverStore.length; i++) {
            SimulatedServer server = new SimulatedServer(serversInformation[i]);
            this.serverStore[i] = server;
        }

        SimulatedServer largestServer = this.getLargestServer();
        this.largestServerType = largestServer.getServerType();
    }

    public void setMostRecentlyUsedServer(SimulatedServer server) {
        this.mostRecentlyUsedServer = server;
    }

    public void printServerStore() {
        for (SimulatedServer simulatedServer: this.serverStore) {
            simulatedServer.display();
        }
    }

    public String getTypeOfLargestServer() {
        return this.largestServerType;
    }

    public SimulatedServer[] getServerStore() {
        return serverStore;
    }

    public SimulatedServer getLargestServer() {
        SimulatedServer largest = this.serverStore[0];
        for (SimulatedServer simulatedServer: this.serverStore) {
            if (simulatedServer.getNumberOfCores() > largest.getNumberOfCores()) {
                largest = simulatedServer;
            }
        }
        return largest;
    }

    public SimulatedServer findNextServerByType(String serverType) {
        SimulatedServer[] listOfServers = this.findServersByType(serverType);
        SimulatedServer nextServer = getNextServer(listOfServers);
        return nextServer;
    }

    public SimulatedServer[] findServersByType(String serverType) {
        SimulatedServer[] list = new SimulatedServer[0];
        for (int i = 0; i < this.serverStore.length; i++) { // what happens if the largest type disappears entirely?
            SimulatedServer server = this.serverStore[i];
            if (server.getServerType().equals(serverType)) list = add_element(list, server);
        }
        return list;
    }

    public static SimulatedServer[] add_element(SimulatedServer[] myarray, SimulatedServer server) { 
        SimulatedServer newArray[] = new SimulatedServer[myarray.length + 1]; 
        for (int i = 0; i < myarray.length; i++) newArray[i] = myarray[i];
        newArray[myarray.length] = server; 
        return newArray; 
    } 

    public SimulatedServer getNextServer(SimulatedServer[] listOfServers) {
        // we haven't used a server at all yet. Just return the first one.
        if (mostRecentlyUsedServer == null) return listOfServers[0];

        // we have used a server. Find the next one based on ID
        for (SimulatedServer simulatedServer: listOfServers) {
            if (simulatedServer.getID() > this.mostRecentlyUsedServer.getID()) {
                // the ID of this server is greater than our previous one. this must be the next one
                return simulatedServer;
            }
        }
        // at this point, we had used up the last server. We want to return the first one so we start again
        return listOfServers[0];
    }
}
