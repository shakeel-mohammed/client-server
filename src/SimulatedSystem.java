
public class SimulatedSystem {
    private SimulatedServer[] serverStore;

    SimulatedSystem(int numServers, String strigifiedServers) {
        this.serverStore = new SimulatedServer[numServers];
        String[] serversInformation = strigifiedServers.split("\n");

        // we will need a normal for-loop here because we need an iterator to store each server in the correct index of the array

        for (String simulatedServer: serversInformation) {
            System.out.println(simulatedServer);
            SimulatedServer server = new SimulatedServer(simulatedServer);
            server.display();
        }
    }

    public SimulatedServer[] getServerStore() {
        return serverStore;
    }

    public SimulatedServer getLargestServer() {
        return serverStore[0]; // should find the largest server in the array and return that rather than 1st in array
    }
}
