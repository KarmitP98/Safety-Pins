package bean;

public class VisitEventBean {

    private String day, bid;
    private String eventType;

    public VisitEventBean(String day, String bid, String eventType) {
        super();
        this.day = day;
        this.bid = bid;
        this.eventType = eventType;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }


}
