package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.VisitEventType;

public class VisitEventDAO extends DAO {


	
	public VisitEventDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addVisitEvent(String day, String bid, VisitEventType type) throws SQLException {
		String query = "insert into VisitEvent values(?,?,?)";
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(query);
	
		stmt.setString(1, day);
		stmt.setString(2, bid);
		stmt.setString(3, type.toString());
		
		stmt.executeUpdate();
		
	}
	
	// to be added
	// get listofVisitEvents by
}
