package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.CardBean;
import bean.POItemBean;

public class CardDAO extends DAO {

	public CardDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CardBean retrieveByUserId(int uid) throws SQLException {
		String query = "select * from card where uid=" + uid;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		CardBean card = null;
		
		while(r.next()) {
			int id = r.getInt("id");
			String cardNumber = r.getString("cardNumber");
			String cvc = r.getString("cvc");
			Date expiry = r.getDate("expiryDate");

			card = new CardBean(id, uid, cardNumber, cvc, expiry);
			
		}
		return card;
		
	}
	
	public int addCard(int uid, String cardNumber, String cvc, Date expiryDate) throws SQLException {
		String query = "insert into card values(null,?,?,?,?)";
			
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, uid);
		stmt.setString(2,cardNumber);
		stmt.setString(3, cvc);
		stmt.setDate(4, expiryDate);
		
		return stmt.executeUpdate();
				
	}

}
