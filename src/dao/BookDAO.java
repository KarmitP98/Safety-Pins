package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bean.BookBean;
import bean.BookType;
import bean.UserBean;

public class BookDAO {

	private DataSource ds;
	
	public BookDAO() throws ClassNotFoundException {
		try {
		ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			e.printStackTrace();
			
		}
	}
	
	
	// methods to be added
	

	
	public BookBean retrieveBookByID(String bookID) throws SQLException {
		
		String query = "select * from Book where bid=" + bookID;
		BookBean bb = null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		while (r.next()) {
			
			String bid = r.getString("bid");
			String title = r.getString("title");
			double price = r.getDouble("price");
			String author = r.getString("author");
			
			BookType category = BookType.valueOf(r.getString("category"));
			
			String picture = r.getString("picture");
			String description = r.getString("description");
			int quantitySold = r.getInt("sold");
			
			bb = new BookBean(bid, title, price, author, category, picture, description, quantitySold);
		
		}
		r.close();
		p.close();
		con.close();
		
		return bb;
	}
	
	public List<BookBean> retrieveAllBooks() throws SQLException {
		String query = "select * from Book";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		ArrayList<BookBean> list = new ArrayList<BookBean>();
		
		
		while (r.next()) {
			
			String bid = r.getString("bid");
			String title = r.getString("title");
			double price = r.getDouble("price");
			String author = r.getString("author");
			
			BookType category = BookType.valueOf(r.getString("category"));
			
			String picture = r.getString("picture");
			String description = r.getString("description");
			int quantitySold = r.getInt("sold");
			
			list.add(new BookBean(bid, title, price, author, category, picture, description, quantitySold));
			
		}
		
		r.close();
		p.close();
		con.close();
		
		return list;
		
		
	}
	
	public List<BookBean> retrieveBooksByCategory(BookType type) throws SQLException {
		String query = "select * from Book where category like '%" + type +"%'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		ArrayList<BookBean> list = new ArrayList<BookBean>();
		
		
		while (r.next()) {
			
			String bid = r.getString("bid");
			String title = r.getString("title");
			double price = r.getDouble("price");
			String author = r.getString("author");
			
			BookType category = BookType.valueOf(r.getString("category"));
			
			String picture = r.getString("picture");
			String description = r.getString("description");
			int quantitySold = r.getInt("sold");
			
			list.add(new BookBean(bid, title, price, author, category, picture, description, quantitySold));
			
		}
		
		r.close();
		p.close();
		con.close();
		
		return list;
	}
	
	
}
