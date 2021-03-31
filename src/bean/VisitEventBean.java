package bean;

public class VisitEventBean {

	private String day, bid;
	private VisitEventType eventType;
	
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

	public VisitEventType getEventType() {
		return eventType;
	}

	public void setEventType(VisitEventType eventType) {
		this.eventType = eventType;
	}

	public VisitEventBean(String day, String bid, VisitEventType eventType) {
		super();
		this.day = day;
		this.bid = bid;
		this.eventType = eventType;
	}
	
	
}
