package lagatrix.server.entities.dto.event;

import java.io.Serializable;
import java.util.Objects;

/**
 * This entoty represents an event of crontab.
 *
 * @author javierfh03
 * @since 0.1
 */
public class Event implements Serializable {
    private String command, minute, hour, dayMonth, month, dayWeek;

    public Event() {
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getDayMonth() {
        return dayMonth;
    }

    public void setDayMonth(String dayMonth) {
        this.dayMonth = dayMonth;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(String dayWeek) {
        this.dayWeek = dayWeek;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.command);
        hash = 79 * hash + Objects.hashCode(this.minute);
        hash = 79 * hash + Objects.hashCode(this.hour);
        hash = 79 * hash + Objects.hashCode(this.dayMonth);
        hash = 79 * hash + Objects.hashCode(this.month);
        hash = 79 * hash + Objects.hashCode(this.dayWeek);
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
        final Event other = (Event) obj;
        if (!Objects.equals(this.command, other.command)) {
            return false;
        }
        if (!Objects.equals(this.minute, other.minute)) {
            return false;
        }
        if (!Objects.equals(this.hour, other.hour)) {
            return false;
        }
        if (!Objects.equals(this.dayMonth, other.dayMonth)) {
            return false;
        }
        if (!Objects.equals(this.month, other.month)) {
            return false;
        }
        return Objects.equals(this.dayWeek, other.dayWeek);
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %s %s %s", minute, hour, dayMonth, month, dayWeek, command);
    }
}
