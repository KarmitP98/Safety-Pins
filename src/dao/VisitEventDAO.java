package dao;

import bean.VisitEventType;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VisitEventDAO extends DAO {


<<<<<<< HEAD
	
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
=======

    private DataSource ds;

    public VisitEventDAO() throws ClassNotFoundException {
        try {
            ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
        } catch (NamingException e) {
            e.printStackTrace();

        }
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
>>>>>>> f0c5a6f4ac32b9a23c5425e055a2861a0dfe8dfd
}
