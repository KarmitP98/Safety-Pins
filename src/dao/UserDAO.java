package dao;

import bean.UserBean;

import java.sql.*;

public class UserDAO extends DAO {

    public UserDAO() throws ClassNotFoundException {
        super();
        // TODO Auto-generated constructor stub
    }

    private UserBean fetchUserBean(ResultSet r) throws SQLException {
        UserBean userBean = null;

        while (r.next()) {
            int userID = r.getInt("id");

            String fName = r.getString("fName");
            String lName = r.getString("lName");
            String email = r.getString("email");
            String password = r.getString("password");
            String type = r.getString("userType");

            userBean = new UserBean(userID, fName, lName, email, password, type);
        }

        return userBean;
    }

    private UserBean fetchQuery(String query) {
        UserBean userBean = null;
        try {
            Connection con = this.ds.getConnection();
            PreparedStatement p = con.prepareStatement(query);
            ResultSet r = p.executeQuery();

            userBean = this.fetchUserBean(r);

            r.close();
            p.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userBean;
    }

    public UserBean fetchUserByID(int id) {
        return this.fetchQuery("select * from user where id = '" + id+"'");
    }

    public UserBean fetchUserByEmail(String userEmail) {
        UserBean user = null;
        return this.fetchQuery("select * from user where email = '" + userEmail+"'");
    }

    /**
     * Fetch user using email and password
     *
     * @param email
     * @param password
     * @return
     */
    public UserBean fetchUserbyEmailandPassword(String email, String password) {
        return this.fetchQuery("select * from user where email = '" + email + "' and password = '" + password+"'");
    }

    public int register(String fName, String lName, String email, String password) throws Exception {
        if (this.fetchUserByEmail(email) != null) {
            throw new Exception("The user with the email exists!");
        }

        System.out.println("Email is trying to register!");

        String preparedStatement = "INSERT INTO User (fName, lName, email, password) VALUES (?,?,?,?)";
        Connection con = this.ds.getConnection();
        PreparedStatement stmt = con.prepareStatement(preparedStatement);
        stmt.setString(1, fName);
        stmt.setString(2, lName);
        stmt.setString(3, email);
        stmt.setString(4, password);

        return stmt.executeUpdate();
    }

    public void readAndPrintTableToConsole() throws SQLException {

        //            DataSource ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
        Connection con = ds.getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM USER");
        while (rs.next()) {
            String em = rs.getString("ID");
            String fname = rs.getString("FName");
            String userType = rs.getString("userType");
            String email = rs.getString("email");
            System.out.println("email = " + email);
            String password = rs.getString("password");
            System.out.println("password = " + password);
            System.out.println("\t" + em + ",\t" + fname + "\t " + "\t " + userType);
        } // end while loop
        con.close();
    }


}
