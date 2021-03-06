package bean;

public class UserBean {
    private int userID;
    private String fName, lName, email, password, userType;

    public UserBean(int userID, String fName, String lName, String email, String password, String userType) {
        super();
        this.userID = userID;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.userType = userType; //'Costumer','Administrator','Partner'
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setType(String type) {
        this.userType = userType;
    }


}
