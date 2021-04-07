package dao;

import bean.POBean;
import bean.POItemBean;
import bean.POStatus;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PODAO extends DAO{
	
	
	
	public PODAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	public POBean retrievePOByID(int pid) throws SQLException {
		
		String query = "select * from PO where id =" + pid;
		POBean po = null; 
		
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();

		
		while (r.next()) {
			int id = r.getInt("id");
			String userID = r.getString("uId");
			POStatus status = POStatus.valueOf(r.getString("status"));
			int addressID = r.getInt("address");
			
			
			po = new POBean(id, userID, status, addressID);
		}
		r.close();
		p.close();
		con.close();
		
		return po;
	
		
	}
	
	public int addPO( int uid, POStatus status, int addressId) throws SQLException {
		String query = "insert into PO values(null,?,?,?)";
		
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, uid);
		stmt.setString(2, status.toString());
		stmt.setInt(3, addressId);
		
		return stmt.executeUpdate();
	}
	
	
	public ArrayList<POItemBean> retrievePOItemsById(int id) throws SQLException {
		
		String query = "select * from POItem where id=" + id;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		ArrayList<POItemBean> items = new ArrayList<POItemBean>();
		
		while(r.next()) {
			String bid = r.getString("bid");
			int price = r.getInt("price");
			int quantity = r.getInt("quantity");
			
			
			items.add(new POItemBean(id, bid, price, quantity));
		}
		return items;
		
	}
	public int addPOItem(int id, String bid, int price, int quantity ) throws SQLException {
		
		String query = "insert into POItem values(?,?,?,?)";
		
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, id);
		stmt.setString(2, bid);
		stmt.setInt(3, price);
		stmt.setInt(4, quantity);
		
		return stmt.executeUpdate();
		
		
	}

}
