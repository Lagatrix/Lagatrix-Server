package lagatrix.server.entities.dto.partition;

import java.io.Serializable;
import java.util.Objects;

/**
 * This entity represents a partiton.
 *
 * @author javierfh03
 * @since 0.1
 */
public class Partition implements Serializable {
    
    private String fileSystem, mountPath, size, avatible, usedStorage;
    private int percentage;

    public Partition() {
    }

    public String getFileSystem() {
        return fileSystem;
    }

    public void setFileSystem(String fileSystem) {
        this.fileSystem = fileSystem;
    }

    public String getMountPath() {
        return mountPath;
    }

    public void setMountPath(String mountPath) {
        this.mountPath = mountPath;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getAvatible() {
        return avatible;
    }

    public void setAvatible(String avatible) {
        this.avatible = avatible;
    }

    public String getUsedStorage() {
        return usedStorage;
    }

    public void setUsedStorage(String usedStorage) {
        this.usedStorage = usedStorage;
    }

    public int getPercernage() {
        return percentage;
    }

    public void setPercernage(int percernage) {
        this.percentage = percernage;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.fileSystem);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Partition other = (Partition) obj;
        if (!Objects.equals(this.fileSystem, other.fileSystem)) {
            return false;
        }
        return Objects.equals(this.mountPath, other.mountPath);
    }

    @Override
    public String toString() {
        return "Partition{" + "fileSystem=" + fileSystem + ", mountPath=" + mountPath + ", size=" + size + ", avatible=" + avatible + ", usedStorage=" + usedStorage + ", percernage=" + percentage + '}';
    }
}
