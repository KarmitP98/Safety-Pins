package dao;

import bean.BookBean;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO extends DAO {


	

	
	public BookDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}

	public BookBean retrieveBookByID(String bookID) throws SQLException {
		
		String query = "select * from Book where bid = ?";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		p.setString(1, bookID);
		ResultSet r = p.executeQuery();
		
		BookBean bb = null;
		
		while(r.next()) {
			String title = r.getString("title");
			double price = r.getDouble("price");
			String author = r.getString("author");
			
			String category = r.getString("category");
			
			String picture = r.getString("picture");
			String description = r.getString("description");
			int quantitySold = r.getInt("sold");
			
			bb = new BookBean(bookID, title, price, author, category, picture, description, quantitySold);
		
		}
		r.close();
		p.close();
		con.close();
		
		return bb;
	}
	
	public ArrayList<BookBean> retrieveAllBooks() throws SQLException {
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
			
			String category = r.getString("category");
			
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
	
	public ArrayList<BookBean> retrieveBooksByCategory(String string) throws SQLException {
		String query = "select * from Book where category like '%" + string +"%'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		ArrayList<BookBean> list = new ArrayList<BookBean>();
		
		
		while (r.next()) {
			
			String bid = r.getString("bid");
			String title = r.getString("title");
			double price = r.getDouble("price");
			String author = r.getString("author");
			
			String category = r.getString("category");
			
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
	
	public ArrayList<BookBean> fetchBookbyNameandCategory(String name, String category) throws SQLException {
		
		String query = "select * from Book where title like '%" + name + "%' and category like '%" + category +"%'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		ArrayList<BookBean> books = new ArrayList<BookBean>();
		
		while(r.next()) {
			
			String bid = r.getString("bid");
			String title = r.getString("title");
			double price = r.getDouble("price");
			String author = r.getString("author");
			
			String cat = r.getString("category");
			
			String picture = r.getString("picture");
			String description = r.getString("description");
			int quantitySold = r.getInt("sold");			
			books.add(new BookBean(bid, title, price, author, cat, picture, description, quantitySold));		
		}	
		return books;
	}
	
	public ArrayList<BookBean> searchBooksByTitleOrAuthor(String keyword) throws SQLException {
		
		String query = "select * from Book where title like '%" + keyword + "%' or author like '%" + keyword +"%'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		ArrayList<BookBean> books = new ArrayList<BookBean>();
		
		while(r.next()) {
			
			String bid = r.getString("bid");
			String title = r.getString("title");
			double price = r.getDouble("price");
			String author = r.getString("author");
			
			String category = r.getString("category");
			
			String picture = r.getString("picture");
			String description = r.getString("description");
			int quantitySold = r.getInt("sold");			
			books.add(new BookBean(bid, title, price, author, category, picture, description, quantitySold));		
		}	
		return books;
	}
	
	public void updateQuantitySold(String bid, int quantitySold) throws SQLException {
		BookBean book = this.retrieveBookByID(bid);
		String query = "update Book set sold = ? where bid= ?";
		
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, quantitySold + book.getQuantitySold());
		stmt.setString(2, bid);
		
		stmt.executeUpdate();
	}
	
	public ArrayList<BookBean> top10() throws SQLException {
		
		String query = "select top 10 * from Book order by sold desc";	
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		ArrayList<BookBean> books = new ArrayList<BookBean>();	
		
		while(r.next()) {
			String bid = r.getString("bid");
			String title = r.getString("title");
			double price = r.getDouble("price");
			String author = r.getString("author");
			
			String category = r.getString("category");
			
			String picture = r.getString("picture");
			String description = r.getString("description");
			int quantitySold = r.getInt("sold");
			books.add(new BookBean(bid, title, price, author, category, picture, description, quantitySold));
		}
		return books;
	}
}
