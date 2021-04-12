package dao;

import bean.CardBean;

import java.sql.*;

public class CardDAO extends DAO {

    public CardDAO() throws ClassNotFoundException {
        super();
        // TODO Auto-generated constructor stub
    }

    public CardBean retrieveByUserId(int uid) {
        CardBean card = null;
        try {
            String query = "select * from card where uid= ?";
            Connection con = this.ds.getConnection();
            PreparedStatement p = con.prepareStatement(query);
            p.setInt(1, uid);
            ResultSet r = p.executeQuery();


            while (r.next()) {
                int id = r.getInt("id");
                String cardNumber = r.getString("cardNumber");
                String cvc = r.getString("cvc");
                String expiry = r.getString("expiryDate");

                card = new CardBean(id, uid, cardNumber, cvc, expiry);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return card;

    }

    public int addCard(int uid, String cardNumber, String cvc, String expiryDate) throws SQLException {
        String query = "insert into card (uid, cardnumber, cvc, expirydate) values(?,?,?,?)";

        Connection con = this.ds.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, uid);
        stmt.setString(2, cardNumber);
        stmt.setString(3, cvc);
        stmt.setString(4, expiryDate);

        return stmt.executeUpdate();

    }

}
