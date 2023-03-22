package lagatrix.server.entities.devices;

/**
 * This entity reperents a CPU.
 * 
 * @author javier
 * @since 1.0
 */
public class CPU {
    private String model;
    private int cores, threads;
    private float minSpeed, maxSpeed;

    public CPU() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public float getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(float minSpeed) {
        this.minSpeed = minSpeed;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "CPU{" + "model=" + model + ", cores=" + cores + ", threads=" + threads + ", minSpeed=" + minSpeed + ", maxSpeed=" + maxSpeed + "}'";
    }
}
