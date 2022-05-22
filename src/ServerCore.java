
public class ServerCore {
    private int id;
    private boolean isBeingUsed;

    // Zero means it's not being used
    private double timeUntilAvailable = 0;

    ServerCore(int id) {
        this.id = id;
        this.isBeingUsed = false;
    }

    public int getID() {
        return this.id;
    }

    public boolean isBeingUsed() {
        return this.isBeingUsed;
    }

    public double timeUntilAvailable() {
        return this.timeUntilAvailable;
    }

    public void reserveForDuration(double duration) {
        this.timeUntilAvailable = duration;
        this.isBeingUsed = true;
        return;
    }

    public void display() {
        System.out.println("=======");
        System.out.println("isBeingUsed: " + isBeingUsed);
        System.out.println("timeUntilAvailable: " + timeUntilAvailable);
        System.out.println("=======");
    }
}
