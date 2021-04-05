package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.AddressBean;
import bean.ReviewBean;

public class AddressDAO {


	private DataSource ds;
	
	public AddressDAO() throws ClassNotFoundException {
		try {
		ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
			
		}
	}
	
	public AddressBean retrieveAddressByUserId(int uid) throws SQLException {
		
	String query = "select * from Address where uId=" +uid;
		
		AddressBean address = null;
		
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
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
	
	public int addAddress(int uid, String street, String province, String country, String zip, String phone ) throws SQLException {
		
		
		String query = "insert into Address values(null,?,?,?,?,?,?)";
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