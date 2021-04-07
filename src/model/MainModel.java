package model;

import bean.BookBean;
import bean.BookType;
import bean.BookWrapper;
import bean.UserBean;
import dao.BookDAO;
import dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class MainModel {

    private static MainModel instance;
    private UserDAO userDAO;
    private BookDAO bookDAO;
    //DAOs need to be declared

    private MainModel() {

        try {

            this.userDAO = new UserDAO();
            this.bookDAO = new BookDAO();

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public static MainModel getInstance() {

        if (instance == null) {
            instance = new MainModel();

            // DAOs need to be instantiated
        }

        return instance;

    }


    /*
     * User
     */

    // save the users information in the session scope.
    public void logIn(HttpServletRequest request, String email, String password) throws Exception {
        UserBean user = this.userDAO.retrieveByEmail(email);

        // check if user is registered in the db
        if (user == null) {
            throw new Exception("Invalid user email!");
        }
        // check if the password is correct
        if (user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
        }
    }

    public void logOut(HttpServletRequest request) {
        request.getSession().setAttribute("user", null);
    }

    public void register(String fName, String lName, String email, String password, HttpServletRequest request) throws Exception {
        this.userDAO.register(fName, lName, email, password);
    }

    /*
     * Cart
     */

    public void addToCart(String bid, HttpServletRequest request) {


    }

    public void removeFromCart(String bid, HttpServletRequest request) {
        HashMap<String, Integer> cart = this.getCart(request);


    }

    public void emptyCart(HttpServletRequest request) {
        request.getSession().setAttribute("cart", new HashMap<String, Integer>());
    }

    public HashMap<String, Integer> getCart(HttpServletRequest request) {
        HashMap<String, Integer> cart = null;

        if (request.getSession().getAttribute("cart") == null) {
            cart = new HashMap<String, Integer>();
        } else {
            cart = (HashMap<String, Integer>) request.getSession().getAttribute("cart");
        }
        return cart;

    }


    /*
     * Book
     */

    public void addReview() {

    }

    /*
     * For Rest Services
     */
    // getProductInfo(bid)
    public String getProductInfo(String bid) throws SQLException, JAXBException, IOException {
        BookBean book = this.bookDAO.retrieveBookByID(bid);
        String title = book.getTitle();
        double price = book.getPrice();
        String author = book.getAuthor();
        BookType category = book.getCategory();
        String picture = book.getPicture();
        String description = book.getDescription();
        int quantitySold = book.getQuantitySold();

        BookWrapper wrapper = new BookWrapper(bid, title, price, author, category, picture, description, quantitySold);

        JAXBContext jc = JAXBContext.newInstance(wrapper.getClass());
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

        StringWriter sw = new StringWriter();
        sw.write("\n");
        marshaller.marshal(wrapper, new StreamResult(sw));


        FileWriter fw = new FileWriter("exportedFile");
        fw.write(sw.toString());
        fw.close();
        return sw.toString();
    }

    // getOrdersByPartNumber(bid)
    public String getOrdersByPartNumber(String bid) {

        // to be added

        return null;
    }

    /*
     * For Analytics
     */


    /*
     * Payments
     */

}
