package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.UserBean;

public class UserDAO extends DAO{
	
	
	public UserDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}



	public UserBean retrieveByEmail(String userEmail) throws SQLException {
			
			String query = "select * from user where email =" + userEmail;
			UserBean user = null;
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet r = p.executeQuery();
			
			while (r.next()) {
				int userID = r.getInt("id");
				String fName = r.getString("fName");
				String lName = r.getString("lName");
				String email = r.getString("email");
				String password = r.getString("password");
				
				user = new UserBean(userID, fName, lName, email, password);
			}
			r.close();
			p.close();
			con.close();
			
			return user;
		}
	
	
	
	public int register ( String fName, String lName, String email, String password) throws Exception {
		
		if (this.retrieveByEmail(email) != null) {
			throw new Exception("The user with the email exists!");
		}
		
		String preparedStatement = "insert into user values(null,?,?,?,?)";
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1,fName);
		stmt.setString(2, lName);
		stmt.setString(3, email);
		stmt.setString(4, password);
		return stmt.executeUpdate();
		
	}
}
