import java.io.DataInputStream;
import java.io.DataOutputStream;

public class SimulatedSystem {
    private SimulatedServer[] serverStore;

    SimulatedSystem(int numServers, String strigifiedServers, DataInputStream in, DataOutputStream out) {
        this.serverStore = new SimulatedServer[numServers];
        String[] serversInformation = strigifiedServers.split("\n");

        for (int i = 0; i < this.serverStore.length; i++) {
            SimulatedServer server = new SimulatedServer(serversInformation[i], in, out);
            this.serverStore[i] = server;
        }
    }

    public void printServerStore() {
        for (SimulatedServer simulatedServer: this.serverStore) {
            simulatedServer.display();
        }
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
}
