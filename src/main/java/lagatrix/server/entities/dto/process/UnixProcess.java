package lagatrix.server.entities.dto.process;

/**
 * This entoty represents an Linux process.
 *
 * @author javierfh03
 * @since 0.1
 */
public class UnixProcess {
    public int PID;
    public String command, username;
    public float useCPU, useRAM;

    public UnixProcess() {
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getUseCPU() {
        return useCPU;
    }

    public void setUseCPU(float useCPU) {
        this.useCPU = useCPU;
    }

    public float getUseRAM() {
        return useRAM;
    }

    public void setUseRAM(float useRAM) {
        this.useRAM = useRAM;
    }

    @Override
    public String toString() {
        return "Process{" + "PID=" + PID + ", command=" + command + ", username=" + username + ", useCPU=" + useCPU + ", useRAM=" + useRAM + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.PID;
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
        final UnixProcess other = (UnixProcess) obj;
        return this.PID == other.PID;
    }
}
