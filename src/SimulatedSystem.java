import java.util.ArrayList;

public class SimulatedSystem {
    private ClientServerConnection connection = ClientServerConnection.getInstance();
    private ArrayList<SimulatedServer> serverStore = new ArrayList<SimulatedServer>();
    private String largestServerType;

    public void queryDSSim(String query) {
        String responseToQuery = connection.sendMessage(query);
        System.out.println(responseToQuery);

        String[] queryIndicativeInformation = responseToQuery.split(" ");
        int numberOfServers = Integer.parseInt(queryIndicativeInformation[1]);
        int lengthPerRecord = Integer.parseInt(queryIndicativeInformation[2]);
        int adjustedRecordLength = lengthPerRecord + 3; // adding some buffer
        byte[] buffer = new byte[numberOfServers * adjustedRecordLength];

        try {
            String responseToOK1 = connection.sendMessage("OK", buffer);
            this.refreshServerStore(responseToOK1);
            
            String responseToOK2 = connection.sendMessage("OK");
            if (!responseToOK2.trim().equals(".")) {
                throw new Error("Unexpected ACK response from ds-sim server: " + responseToOK2);
            }
        } catch (Exception exc) {
            System.out.println("exception: " + exc);
        }
    }

    public void refreshServerStore(String strigifiedServers) {
        String[] serversInformation = strigifiedServers.split("\n");
        for (String server: serversInformation) this.serverStore.add(new SimulatedServer(server));

        this.largestServerType = this.getLargestServer().getServerType();
    }

    public void printServerStore() {
        for (SimulatedServer simulatedServer: this.serverStore) {
            simulatedServer.display();
        }
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
}
