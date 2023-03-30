package lagatrix.server.entities.dto.hardware;

/**
 * This entity reperents a RAM.
 *
 * @author javierfh03
 * @since 0.1
 */
public class RAM {
    private String unitCapacity;
    private int capacity;

    public RAM() {
    }

    public String getUnitCapacity() {
        return unitCapacity;
    }

    public void setUnitCapacity(String unitCapacity) {
        this.unitCapacity = unitCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "RAM{" + "unitCapacity=" + unitCapacity + ", capacity=" + capacity + '}';
    }
}
