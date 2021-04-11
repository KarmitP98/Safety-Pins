package dao;

import bean.POBean;
import bean.POItemBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PODAO extends DAO {


    public PODAO() throws ClassNotFoundException {
        super();
        // TODO Auto-generated constructor stub
    }

    public POBean retrievePOByID(int pid) throws SQLException {

        String query = "select * from PO where id = ?";
        POBean po = null;

        Connection con = this.ds.getConnection();
        PreparedStatement p = con.prepareStatement(query);
        p.setInt(1, pid);
        ResultSet r = p.executeQuery();


        while (r.next()) {
            int id = r.getInt("id");
            String userID = r.getString("uId");
            String status = r.getString("status");
            int addressID = r.getInt("address");
            String date = r.getString("purchasedate");


            po = new POBean(id, userID, status, addressID, date);
        }
        r.close();
        p.close();
        con.close();

        return po;


    }
    // adds a new Purchase order and returns the poId
    public int addPO(int uid, String status, int addressId, String date) throws SQLException {
        String query = "insert into PO values(null,?,?,?,?)";

        Connection con = this.ds.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, uid);
        stmt.setString(2, status.toString());
        stmt.setInt(3, addressId);
        stmt.setString(4, date);
        stmt.executeUpdate();
        
        query = "select * from PO where id = (select max(id) from PO)";
        stmt = con.prepareStatement(query);
        
        ResultSet r = stmt.executeQuery();
        
        return r.getInt("id");
        
    }

    public int updatePOStatus(int id, String status) throws SQLException {
        String query = "update PO set status = ? where id = ?";
        Connection con = this.ds.getConnection();
        PreparedStatement p = con.prepareStatement(query);
        p.setString(1, status);
        p.setInt(2, id);
        return p.executeUpdate();
    }

    public ArrayList<POItemBean> retrievePOItemsById(int id) throws SQLException {

        String query = "select * from POItem where id= ?";
        Connection con = this.ds.getConnection();
        PreparedStatement p = con.prepareStatement(query);
        p.setInt(1, id);
        ResultSet r = p.executeQuery();

        ArrayList<POItemBean> items = new ArrayList<POItemBean>();

        while (r.next()) {
            String bid = r.getString("bid");
            double price = r.getDouble("price");
            int quantity = r.getInt("quantity");


            items.add(new POItemBean(id, bid, price, quantity));
        }
        return items;

    }

    public ArrayList<POItemBean> retrievePOItemsByBookId(String bid) throws SQLException {

        String query = "select * from POItem where bid= ?";
        Connection con = this.ds.getConnection();
        PreparedStatement p = con.prepareStatement(query);
        p.setString(1, bid);
        ResultSet r = p.executeQuery();

        ArrayList<POItemBean> items = new ArrayList<POItemBean>();

        while (r.next()) {
            int id = r.getInt("id");
            double price = r.getDouble("price");
            int quantity = r.getInt("quantity");


            items.add(new POItemBean(id, bid, price, quantity));
        }
        return items;

    }

    public int addPOItem(int id, String bid, double price, int quantity) throws SQLException {

        String query = "insert into POItem values(?,?,?,?)";

        Connection con = this.ds.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);
        stmt.setInt(1, id);
        stmt.setString(2, bid);
        stmt.setDouble(3, price);
        stmt.setInt(4, quantity);

        return stmt.executeUpdate();


    }

    public String getListOfPOItems(String bid) throws SQLException {
        String query = "SELECT * FROM  POItem  INNER JOIN PO ON PO.id = POItem.id where bid = 'b001'";

        Connection con = this.ds.getConnection();
        PreparedStatement stmt = con.prepareStatement(query);

        ResultSet r = stmt.executeQuery();
        String result = "";
        while (r.next()) {
            int id = r.getInt("id");
            String bookId = r.getString("bid");

            result += id + bookId;

            // create poitembean
            // addressbean
            //
            //create OrderWrapper

        }

        return result;
    }
}
