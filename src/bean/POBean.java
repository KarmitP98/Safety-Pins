package bean;

public class POBean {
    private int id;
    private int userID;
    private String status;
    private int addressID;
    private String date;

    public POBean(int id, int userID, String status, int addressID, String date) {
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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
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
