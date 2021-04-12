package dao;

import bean.BookBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookDAO extends DAO {


    public BookDAO() throws ClassNotFoundException {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Return the a list of books retrieved from the result set
     *
     * @param r
     * @return
     */
    private ArrayList<BookBean> fetchBookList(ResultSet r) {
        ArrayList<BookBean> temp = new ArrayList<>();
        try {
            while (r.next()) {
                String bid = r.getString("bid");
                String title = r.getString("title");
                double price = r.getDouble("price");
                String author = r.getString("author");

                String category = r.getString("category");

                String picture = r.getString("picture");
                String description = r.getString("description");
                int quantitySold = r.getInt("sold");

                temp.add(new BookBean(bid, title, price, author, category, picture, description, quantitySold));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    /**
     * Get BookBean retrieved from the result set
     *
     * @param r
     * @return
     * @throws SQLException
     */
    private BookBean fetchBook(ResultSet r) throws SQLException {
        BookBean bookBean = null;

        while (r.next()) {
            String bookID = r.getString("bid");
            String title = r.getString("title");
            double price = r.getDouble("price");
            String author = r.getString("author");

            String category = r.getString("category");

            String picture = r.getString("picture");
            String description = r.getString("description");
            int quantitySold = r.getInt("sold");

            bookBean = new BookBean(bookID, title, price, author, category, picture, description, quantitySold);
        }

        return bookBean;
    }

    /**
     * Query the given query statement and return either a BookBean or Arraylist of BookBeans
     *
     * @param query
     * @return
     */
    private Object fetchQuery(String query, boolean one) {
        Object bookBean = new Object();
        try {
            Connection con = this.ds.getConnection();
            PreparedStatement p = con.prepareStatement(query);
            ResultSet r = p.executeQuery();

            bookBean = one ? this.fetchBook(r) : this.fetchBookList(r);

            r.close();
            p.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bookBean;
    }

    /**
     * Fetch Book by bid
     *
     * @param bookID
     * @return
     * @throws SQLException
     */
    public BookBean FetchBookByID(String bookID) throws SQLException {
        return  (BookBean) this.fetchQuery("select * from Book where bid='" + bookID + "'", true);
    }
    //testing
    public BookBean retrieveByBookId(String bid) throws SQLException {
    	String query = "select * from Book where bid = ?";
    	Connection con = this.ds.getConnection();
    	PreparedStatement p = con.prepareStatement(query);
    	p.setString(1, bid);
    	ResultSet r = p.executeQuery();
    	BookBean book = null;
    	while(r.next()) {
    		String title = r.getString("title");
    		double price = r.getDouble("price");
    		String author = r.getString("author");
    		String category = r.getString("category");
    		String picture = r.getString("picture");
    		String description = r.getString("description");
    		int sold = r.getInt("sold");
    		book = new BookBean(bid, title, price, author, category, picture, description, sold);
    	}
    	return book;
    }
    
    
    @SuppressWarnings("unchecked")
	public ArrayList<BookBean> fetchAllBooks() throws SQLException {
        return (ArrayList<BookBean>) this.fetchQuery("select * from Book", false);
    }

    @SuppressWarnings("unchecked")
	public ArrayList<BookBean> fetchBooksByCategory(String string) throws SQLException {
        return (ArrayList<BookBean>) this.fetchQuery("select * from Book where category like '%" + string + "%'", false);
    }

    @SuppressWarnings("unchecked")
	public ArrayList<BookBean> fetchBookbyNameandCategory(String name, String category) throws SQLException {
        return (ArrayList<BookBean>) this.fetchQuery("select * from Book where title like '%" + name + "%' and category like '%" + category + "%'", false);
    }

    @SuppressWarnings("unchecked")
	public ArrayList<BookBean> fetchBooksByTitleOrAuthor(String keyword) throws SQLException {
        return (ArrayList<BookBean>) this.fetchQuery("select * from Book where title like '%" + keyword + "%' or author like '%" + keyword + "%'", false);
    }

    public void updateQuantitySold(String bid, int quantitySold) throws SQLException {
        BookBean book = this.FetchBookByID(bid);
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
	    	ArrayList<BookBean> list = new ArrayList<BookBean>();
	    	while(r.next()) {
		    	String bid = r.getString("bid");
	    		String title = r.getString("title");
	    		double price = r.getDouble("price");
	    		String author = r.getString("author");
	    		String category = r.getString("category");
	    		String picture = r.getString("picture");
	    		String description = r.getString("description");
	    		int sold = r.getInt("sold");
	    		
    		list.add(new BookBean(bid, title, price, author, category, picture, description, sold));
    		}
    	return list;
      }
}
