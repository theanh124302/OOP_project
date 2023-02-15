package models;

public class Event {
    private String Time;
    private String details;
    public String getTime() {
        return Time;
    }
    public String getDetails() {
        return details;
    }
    public void setTime(String time) {
        Time = time;
    }
    public void setDetails(String details) {
        this.details = details;
    }
    public Event(String time, String details) {
        Time = time;
        this.details = details;
    }

}
