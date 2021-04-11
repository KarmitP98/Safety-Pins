package bean;

public class POBean {
    private int id;
    private String userID;
    private String status;
    private int addressID;
    private String date;

    public POBean(int id, String userID, String status, int addressID, String date) {
        super();
        this.id = id;
        this.userID = userID;
        this.status = status;
        this.addressID = addressID;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;

    }

}
