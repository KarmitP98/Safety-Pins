package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bean.CounterBean;
import bean.ReviewBean;

public class CounterDAO extends DAO {

	public CounterDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public void incrementCounter(int uid) throws SQLException {
		 String query = "update Counter set counter = ? where uid = ?";

	        Connection con = this.ds.getConnection();
	        PreparedStatement stmt = con.prepareStatement(query);
	        
	        stmt.setInt(1, this.getCounter(uid).getCounter() + 1);
	        stmt.setInt(2, uid);

	        stmt.executeUpdate();

	}
	public void addCounter(int uid) throws SQLException {
		String query = "INSERT INTO counter (uid) VALUES (?)";
		 Connection con = this.ds.getConnection();
         PreparedStatement p = con.prepareStatement(query);
         p.setInt(1, uid);
         p.executeUpdate();
	}

	public CounterBean getCounter(int uid) throws SQLException {
		
		  String query = "select * from counter where uid= ?";

	        CounterBean counterBean = null;

	        Connection con = this.ds.getConnection();
	        PreparedStatement p = con.prepareStatement(query);
	        p.setInt(1, uid);
	        ResultSet r = p.executeQuery();

	        while (r.next()) {
	         
	          int counter = r.getInt("counter");

	           counterBean = new CounterBean(uid, counter);
	        }

	        return counterBean;

	}
}
