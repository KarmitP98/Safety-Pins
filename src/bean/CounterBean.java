package bean;

public class CounterBean {
	private int uid, counter;

	public CounterBean(int uid, int counter) {
		super();
		this.uid = uid;
		this.counter = counter;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	
	
}
