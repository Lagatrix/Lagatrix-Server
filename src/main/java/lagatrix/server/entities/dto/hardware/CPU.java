package lagatrix.server.entities.dto.hardware;

/**
 * This entity reperents a CPU.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class CPU {
    private String model, cacheMemory;
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

    public String getCacheMemory() {
        return cacheMemory;
    }

    public void setCacheMemory(String cacheMemory) {
        this.cacheMemory = cacheMemory;
    }

    @Override
    public String toString() {
        return "CPU{" + "model=" + model + ", cacheMemory=" + cacheMemory + ", cores=" + cores + ", threads=" + threads + ", minSpeed=" + minSpeed + ", maxSpeed=" + maxSpeed + '}';
    }
}
