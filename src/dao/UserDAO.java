package dao;

import bean.UserBean;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO extends DAO {

	public UserDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserBean retrieveByEmail(String userEmail) throws SQLException {

		String query = "select * from user where email = ?";
		UserBean user = null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, userEmail);
		ResultSet r = p.executeQuery();

		while (r.next()) {
			int userID = r.getInt("id");

			String fName = r.getString("fName");
			String lName = r.getString("lName");
			String email = r.getString("email");
			String password = r.getString("password");
			String type = r.getString("userType");

			user = new UserBean(userID, fName, lName, email, password, type);
		}
		r.close();
		p.close();
		con.close();

		return user;
	}

	public int register(String fName, String lName, String email, String password) throws Exception {

		if (this.retrieveByEmail(email) != null) {
			throw new Exception("The user with the email exists!");
		}
		
		System.out.println("Email is trying to register!");

//		String preparedStatement = "INSERT INTO User (fName, lName, email, password) VALUES (?,?,?,?)";
//		Connection con = this.ds.getConnection();
//		PreparedStatement stmt = con.prepareStatement(preparedStatement);
//		stmt.setString(1, fName);
//		stmt.setString(2, lName);
//		stmt.setString(3, email);
//		stmt.setString(4, password);

//		return stmt.executeUpdate();
		
		return 1;
	}

	public void readAndPrintTableToConsole() throws SQLException {

		try {
			DataSource ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
			Connection con = ds.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM USER");
			while (rs.next()) {
				String em = rs.getString("ID");
				String fname = rs.getString("FName");
				String userType = rs.getString("userType");
				System.out.println("\t" + em + ",\t" + fname + "\t " + "\t " + userType);
			} // end while loop
			con.close();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Fetch user using email and password
	 * @param email
	 * @param password
	 * @return
	 */
	public UserBean fetchUser(String email, String password) {
		UserBean user = null;

		String query = "select * from user where email = ? and password = ?";

		try {
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			p.setString(1, email);
			p.setString(2, password);
			ResultSet r = p.executeQuery();
			
			while (r.next()) {
				
				int userID = r.getInt("id");
				String fName = r.getString("fName");
				String lName = r.getString("lName");
				String e = r.getString("email");
				String pa = r.getString("password");
				String type = r.getString("userType");

				user = new UserBean(userID, fName, lName, email, password, type);
			}

			r.close();
			p.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}

		return user;
	}

}
