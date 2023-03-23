package lagatrix.server.entities.devices;

/**
 * his entity reperents a RAM.
 *
 * @author javier
 * @since 1.0
 */
public class RAM {
    private String unityStorage;
    private int capacity;

    public RAM() {
    }

    public String getUnityStorage() {
        return unityStorage;
    }

    public void setUnityStorage(String unityStorage) {
        this.unityStorage = unityStorage;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "RAM{" + "unityStorage=" + unityStorage + ", capacity=" + capacity + '}';
    }
}
