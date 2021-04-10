package dao;



import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.VisitEventBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VisitEventDAO extends DAO {



	public VisitEventDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	
	// to be added
	// get listofVisitEvents by


    public void addVisitEvent(String day, String bid, String type) throws SQLException {
        String query = "insert into VisitEvent values(?,?,?)";
        Connection con = this.ds.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);

        stmt.setString(1, day);
        stmt.setString(2, bid);
        stmt.setString(3, type.toString());

        stmt.executeUpdate();
        

    }
    public ArrayList<VisitEventBean> retrieveAllVisitEvents() throws SQLException {
    	
    	String query = "select * from VisitEvent";
    	 Connection con = this.ds.getConnection();
         PreparedStatement p = con.prepareStatement(query);
         ResultSet r = p.executeQuery();
         ArrayList<VisitEventBean> list = null;
         while(r.next() ) {
        	 String day = r.getString("day");
        	 String bid = r.getString("bid");
        	 String type = r.getString("type");
        	 
        	 list.add(new VisitEventBean(day, bid, type));
         }
         
         return list;
         
    }

    

}
