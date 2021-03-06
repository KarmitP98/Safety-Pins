package bean;

public class AddressBean {

    private int id, userID;
    private String street, province, country, zip, phone;

    public AddressBean(int id, int userID, String street, String province, String country, String zip, String phone) {
        super();
        this.id = id;
        this.userID = userID;
        this.street = street;
        this.province = province;
        this.country = country;
        this.zip = zip;
        this.phone = phone;
    }

    public AddressBean() {

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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
