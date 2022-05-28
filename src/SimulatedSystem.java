import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SimulatedSystem {
    private ConfigDataLoader configDataLoader = ConfigDataLoader.getInstance();
    private ClientServerConnection clientServerConnection = ClientServerConnection.getInstance();
    
    private ArrayList<SimulatedServer> serverStore = new ArrayList<SimulatedServer>();
    private String largestServerType;

    public ArrayList<SimulatedServer> queryDSSim(String query) {
        String responseToQuery = clientServerConnection.sendMessage(query);
        // System.out.println(responseToQuery);

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

    public ArrayList<SimulatedServer> sortServersByFewestWaitingJobs(ArrayList<SimulatedServer> inputList) {
        Collections.sort(inputList, new Comparator<SimulatedServer>() {
            @Override
            public int compare(SimulatedServer lhs, SimulatedServer rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getNumWaitingJobs() < rhs.getNumWaitingJobs() ? -1 : (lhs.getNumWaitingJobs() > rhs.getNumWaitingJobs()) ? 1 : 0;
            }
        });
        return inputList;
    }

    public SimulatedServer findServerThatCanHandleJobImmediately(ArrayList<SimulatedServer> inputList) {
        ArrayList<SimulatedServer> listOfServers = inputList;
        SimulatedServer lowest = listOfServers.get(0);
        for (SimulatedServer simulatedServer: listOfServers) {
            if (simulatedServer.getEstimatedWaitTime() < lowest.getEstimatedWaitTime()) {
                lowest = simulatedServer;
            }
        }
        return lowest;
    }

    public SimulatedServer findLeastCapableServer(ArrayList<SimulatedServer> inputList) {
        Collections.sort(inputList, new Comparator<SimulatedServer>() {
            @Override
            public int compare(SimulatedServer lhs, SimulatedServer rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending
                return lhs.getNumberOfCores() < rhs.getNumberOfCores() ? -1 : (lhs.getNumberOfCores() > rhs.getNumberOfCores()) ? 1 : 0;
            }
        });
        return inputList.get(0);
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

    public void printStore() {
        for (SimulatedServer simulatedServer: this.serverStore) {
            simulatedServer.display();
        }
    }
}
