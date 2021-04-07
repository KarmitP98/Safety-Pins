package bean;

import java.sql.Date;

public class CardBean {
	
	private int id, uid;
	private String cardNumber, cvc;
	private Date expiryDate;
	
	public CardBean(int id, int uid, String cardNumber, String cvc, Date expiryDate) {
		super();
		this.id = id;
		this.uid = uid;
		this.cardNumber = cardNumber;
		this.cvc = cvc;
		this.expiryDate = expiryDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getCvc() {
		return cvc;
	}

	public void setCvc(String cvc) {
		this.cvc = cvc;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	
	
	

}
