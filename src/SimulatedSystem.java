import java.util.ArrayList;

public class SimulatedSystem {
    private ConfigDataLoader configDataLoader = ConfigDataLoader.getInstance();
    private ClientServerConnection clientServerConnection = ClientServerConnection.getInstance();
    
    private ArrayList<SimulatedServer> serverStore = new ArrayList<SimulatedServer>();
    private String largestServerType;

    public void queryDSSim(String query) {
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
            this.refreshServerStore(responseToOK1);
            
            String responseToOK2 = clientServerConnection.sendMessage("OK");
            if (!responseToOK2.trim().equals(".")) {
                throw new Error("Unexpected ACK response from ds-sim server: " + responseToOK2);
            }
        } catch (Exception exc) {
            System.out.println("exception: " + exc);
        }
    }

    public void refreshServerStore(String strigifiedServers) {
        this.serverStore = new ArrayList<SimulatedServer>();
        String[] serversInformation = strigifiedServers.split("\n");
        for (String server: serversInformation) this.serverStore.add(new SimulatedServer(server));

        this.largestServerType = this.getLargestServer().getServerType();
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
