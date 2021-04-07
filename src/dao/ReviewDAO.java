package dao;

import bean.ReviewBean;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReviewDAO {


<<<<<<< HEAD
public class ReviewDAO extends DAO{

	
	
	public ReviewDAO() throws ClassNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReviewBean retrieveReview(int uid, String bid) throws SQLException {
		String query = "select * from review where uId=" + uid + "and bId=" + bid;
		
		ReviewBean review = null;
		
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		while (r.next()) {
			// using uID for review table ????
			int id = r.getInt("id");
			String bookId = r.getString("bId");
			int userId = r.getInt("uId");
			double rating = r.getDouble("rating");
			String subject = r.getString("subject");
			String description = r.getString("description");
			
			review = new ReviewBean(id, bookId, userId, rating, subject, description);
			
		}
		
		return review;
		
	}
	public ArrayList<ReviewBean> retrieveReviewsbyBookID(String bid) throws SQLException {
		String query = "select * from Review where bid = " + bid;
		ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>();
		
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		while(r.next()) {
			int id = r.getInt("id");
			int userID = r.getInt("uId");
			String bookID = r.getString("bId");
			String description = r.getString("description");
			double rating = r.getDouble("rating");
			String subject = r.getString("subject");
			
			reviews.add(new ReviewBean(id, bookID, userID, rating, subject, description));			
		}
		r.close();
		p.close();
		con.close();
		
		return reviews;
	}
	
	public int addReview(String bid, int uid, double rating, String subject, String description) throws SQLException {
		
		String preparedStatement = "insert into Review values(null,?,?,?,?,?)";
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, bid);
		stmt.setInt(2, uid);
		stmt.setDouble(3, rating);
		stmt.setString(4, subject);
		stmt.setString(5, description);
		
		return stmt.executeUpdate();
		
		
	}
	
	
	
=======
    private DataSource ds;

    public ReviewDAO() throws ClassNotFoundException {
        try {
            ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
        } catch (NamingException e) {
            e.printStackTrace();

        }
    }

    public ReviewBean retrieveReview(int uid, String bid) throws SQLException {
        String query = "select * from review where uId=" + uid + "and bId=" + bid;

        ReviewBean review = null;

        Connection con = this.ds.getConnection();
        PreparedStatement p = con.prepareStatement(query);
        ResultSet r = p.executeQuery();

        while (r.next()) {
            // using uID for review table ????
            int id = r.getInt("id");
            String bookId = r.getString("bId");
            int userId = r.getInt("uId");
            double rating = r.getDouble("rating");
            String subject = r.getString("subject");
            String description = r.getString("description");

            review = new ReviewBean(id, bookId, userId, rating, subject, description);

        }

        return review;

    }

    public ArrayList<ReviewBean> retrieveReviewsbyBookID(String bid) throws SQLException {
        String query = "select * from Review where bid = " + bid;
        ArrayList<ReviewBean> reviews = new ArrayList<ReviewBean>();

        Connection con = this.ds.getConnection();
        PreparedStatement p = con.prepareStatement(query);
        ResultSet r = p.executeQuery();

        while (r.next()) {
            int id = r.getInt("id");
            int userID = r.getInt("uId");
            String bookID = r.getString("bId");
            String description = r.getString("description");
            double rating = r.getDouble("rating");
            String subject = r.getString("subject");

            reviews.add(new ReviewBean(id, bookID, userID, rating, subject, description));
        }
        r.close();
        p.close();
        con.close();

        return reviews;
    }

    public int addReview(String bid, int uid, double rating, String subject, String description) throws SQLException {

        String preparedStatement = "insert into Review values(null,?,?,?,?,?)";
        Connection con = this.ds.getConnection();
        PreparedStatement stmt = con.prepareStatement(preparedStatement);
        stmt.setString(1, bid);
        stmt.setInt(2, uid);
        stmt.setDouble(3, rating);
        stmt.setString(4, subject);
        stmt.setString(5, description);

        return stmt.executeUpdate();


    }


>>>>>>> f0c5a6f4ac32b9a23c5425e055a2861a0dfe8dfd
}