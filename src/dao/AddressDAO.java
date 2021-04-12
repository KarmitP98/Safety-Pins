package dao;

import bean.AddressBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AddressDAO extends DAO {


    public AddressDAO() throws ClassNotFoundException {
        super();
        // TODO Auto-generated constructor stub
    }

    public AddressBean retrieveAddressByUserId(int uid) throws SQLException {

        String query = "select * from Address where uid= ?";

        AddressBean address = null;

        Connection con = this.ds.getConnection();
        PreparedStatement p = con.prepareStatement(query);
        p.setInt(1, uid);
        ResultSet r = p.executeQuery();

        while (r.next()) {
            int id = r.getInt("id");
            String street = r.getString("street");
            String province = r.getString("province");
            String country = r.getString("country");
            String zip = r.getString("zip");
            String phone = r.getString("phone");

            address = new AddressBean(id, uid, street, province, country, zip, phone);
        }

        return address;


    }

    public int addAddress(int uid, String street, String province, String country, String zip, String phone) throws SQLException {


        String query = "insert into Address (uid, street, province, country, zip, phone) values(?,?,?,?,?,?)";
        Connection con = this.ds.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setInt(1, uid);
        stmt.setString(2, street);
        stmt.setString(3, province);
        stmt.setString(4, country);
        stmt.setString(5, zip);
        stmt.setString(6, phone);

        return stmt.executeUpdate();
    }


}