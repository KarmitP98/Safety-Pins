package model;

import bean.*;
import dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainModel {

    private static MainModel instance;
    private UserDAO userDAO;
    private BookDAO bookDAO;
    private CardDAO cardDAO;
    private PODAO poDAO;
    private VisitEventDAO visitEventDAO;
    private ReviewDAO reviewDAO;
    private AddressDAO addressDAO;
    private CounterDAO	counterDAO;
    // DAOs need to be declared

    private MainModel() {

        try {
            this.userDAO = new UserDAO();
            this.bookDAO = new BookDAO();
            this.addressDAO = new AddressDAO();
            this.cardDAO = new CardDAO();
            this.poDAO = new PODAO();
            this.visitEventDAO = new VisitEventDAO();
            this.reviewDAO = new ReviewDAO();
            this.counterDAO = new CounterDAO();

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static MainModel getInstance() {

        if (instance == null) {
            instance = new MainModel();
        }
        return instance;
    }

    /**
     * Fetch user using login credentials. Setup User value in session storage.
     */
    public void logIn(HttpServletRequest request, HttpServletResponse response, String email, String password) throws Exception {
        UserBean user = null;
        user = this.userDAO.fetchUserbyEmailandPassword(email, password);

        if (user != null) {
            request.getSession().setAttribute("user", user);

            // Check user type

            // If Visitor
            response.sendRedirect("/home.jsp");

            // If Admin
            // Redirect to report-page.html
            
        } else {
            response.sendRedirect("/login.html");
        }
    }

    public void logOut(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().removeAttribute("user");
        try {
            response.sendRedirect("/login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void registerUser(String fName, String lName, String email, String password, HttpServletResponse response) {
        try {
            int registered = this.userDAO.register(fName, lName, email, password);
            if (registered == 1) {
                System.out.println("A new user has been registered!");
                response.sendRedirect("/home.jsp");
            } else
                response.sendRedirect("/signup.html");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public String getUserFullNameByUid(int uid) {
    	UserBean user = this.userDAO.fetchUserByID(uid);
    	return user.getfName() + " " + user.getlName();
    }

    // Testing purpose
    public void test() throws SQLException {
        this.userDAO.readAndPrintTableToConsole();
    }

    /*
     * Cart
     */

    public void addToCart(String bid, HttpServletRequest request) {
        BookBean book = this.getBookById(bid);
        HashMap<BookBean, Integer> cart = this.getCart(request);

        int quantity = 1;
        if (cart.containsKey(book)) {
            quantity = cart.get(book) + 1;
        }
        cart.put(book, quantity);
        System.out.println("cart = " + cart);
        request.getSession().setAttribute("cart", cart);
    }

    public void removeFromCart(String bid, HttpServletRequest request) {
        HashMap<BookBean, Integer> cart = this.getCart(request);
        cart.remove(this.getBookById(bid));
        request.getSession().setAttribute("cart", cart);
    }

    public void emptyCart(HttpServletRequest request) {
        request.getSession().removeAttribute("cart");
    }

    public HashMap<BookBean, Integer> getCart(HttpServletRequest request) {
        HashMap<BookBean, Integer> cart = null;

        if (request.getSession().getAttribute("cart") == null) {
            cart = new HashMap<BookBean, Integer>();
        } else {
            cart = (HashMap<BookBean, Integer>) request.getSession().getAttribute("cart");
        }
        return cart;

    }

    public double getTotalPrice(HttpServletRequest request) {
        HashMap<BookBean, Integer> cart = (HashMap<BookBean, Integer>) request.getSession().getAttribute("cart");

        double total = 0;

        Iterator<Map.Entry<BookBean, Integer>> iterator = cart.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry mapElement = (Map.Entry) iterator.next();
            double bookPrice = ((BookBean) mapElement).getPrice();
            int quantity = (int) mapElement.getValue();
            total += bookPrice * quantity;
        }
        return total;
    }

    /*
     * Book
     */
    public ArrayList<BookBean> getBooksByCategory(String category) {

        try {
            return this.bookDAO.fetchBooksByCategory(category);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<BookBean> getAllBooks() {
        try {

            return this.bookDAO.fetchAllBooks();

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public BookBean getBookById(String bid) {
        try {
            return this.bookDAO.FetchBookByID(bid);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    // search books by author name or title //
    public ArrayList<BookBean> searchBooks(String query) {
        try {
            return this.bookDAO.fetchBooksByTitleOrAuthor(query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void addReview(String bid, int uid, double rating, String subject, String description) {
        try {
            this.reviewDAO.addReview(bid, uid, rating, subject, description);
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    public ArrayList<ReviewBean> getReviews(String bid) {

        try {
            return this.reviewDAO.retrieveReviewsbyBookID(bid);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /*
     * For Rest Services
     */
    // getProductInfo(bid)
    public String getProductInfo(String bid) throws SQLException, JAXBException, IOException {
        BookBean book = this.bookDAO.FetchBookByID(bid);
        if (book == null) {
            return null;
        }
        String title = book.getTitle();
        double price = book.getPrice();
        String author = book.getAuthor();
        String category = book.getCategory();
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

        try {
            return this.poDAO.getListOfPOItems(bid);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /*
     * Address
     */
    public AddressBean getAddress(HttpServletRequest request) {
        UserBean user = (UserBean) request.getSession().getAttribute("user");

        AddressBean address = null;
        try {
            address = this.addressDAO.retrieveAddressByUserId(user.getUserID());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return address;
    }

    public void addAddress(HttpServletRequest request, String street, String province, String country, String zip,
                           String phone) {
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        try {
            this.addressDAO.addAddress(user.getUserID(), street, province, country, zip, phone);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    /*
     * Orders
     */


    public int addPO(int uid, String status, int addressId, String date) {
    	int pid = 0;
    	try {
           pid = this.poDAO.addPO(uid, status, addressId, date);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	return pid;
    }

    public POBean getPObyId(int id) {
        try {
            return this.poDAO.retrievePOByID(id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<POItemBean> getPOItemsbyPOId(int id) {
        try {
            return this.poDAO.retrievePOItemsById(id);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<POItemBean> getPOItemsbyBookId(String bid) {
        try {
            return this.poDAO.retrievePOItemsByBookId(bid);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void addPOItem(int id, String bid, double price, int quantity) {
        try {
            this.poDAO.addPOItem(id, bid, price, quantity);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addPOandItems(HttpServletRequest request, String status, String date) throws SQLException {
    	UserBean user = (UserBean) request.getSession().getAttribute("user");
    	HashMap<BookBean, Integer> cart = (HashMap) request.getSession().getAttribute("cart");
    	AddressBean address = this.addressDAO.retrieveAddressByUserId(user.getUserID());
    	
    	//adds a new purchase order
    	int pid = addPO(user.getUserID(), status, address.getId(), date);
    	
    	//adds POItems for each item in the cart
    	 for (Map.Entry<BookBean, Integer> item : cart.entrySet()) {
             
    		 this.addPOItem(pid, item.getKey().getBid(), item.getKey().getPrice(), item.getValue());
         }

    	
    };
    /*
     * Visit Events
     */

    public ArrayList<VisitEventBean> getAllVisitEvents() {
        try {
            return this.visitEventDAO.retrieveAllVisitEvents();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void addVisitEvent(String day, String bid, String type) {
        try {
            this.visitEventDAO.addVisitEvent(day, bid, type);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /*
     * For Analytics
     */

    public ArrayList<BookBean> getTop10() {
        try {
            return this.bookDAO.top10();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<BookBean> fetchBookbyNameandCategory(String name, String category) {
        try {
            return this.bookDAO.fetchBookbyNameandCategory(name, category);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
     * Payments
     */
    public CardBean getCard(HttpServletRequest request) {
        UserBean userBean = (UserBean) request.getSession().getAttribute("user");
        return this.cardDAO.retrieveByUserId(userBean.getUserID());
    }

    public void addCard(int uid, String cardNumber, String cvc, Date expiryDate) {
        try {
            this.cardDAO.addCard(uid, cardNumber, cvc, expiryDate);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void incrementCounter(HttpServletRequest request) {
    	UserBean user = (UserBean) request.getSession().getAttribute("user");
    	try {
			this.counterDAO.incrementCounter(user.getUserID());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
    public int getCounter(HttpServletRequest request) {
    	UserBean user = (UserBean) request.getSession().getAttribute("user");
    	try {
			return this.counterDAO.getCounter(user.getUserID()).getCounter();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return 0;
    }
}
