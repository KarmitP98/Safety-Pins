package model;

import bean.*;
import dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
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
    //DAOs need to be declared

    private MainModel() {

        try {

            this.userDAO = new UserDAO();
            this.bookDAO = new BookDAO();
            this.addressDAO = new AddressDAO();
            this.cardDAO = new CardDAO();
            this.poDAO = new PODAO();
            this.visitEventDAO = new VisitEventDAO();
            this.reviewDAO = new ReviewDAO();

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
    	
    /*
     * User
     */

    // save the users information in the session scope.
    public void logIn(HttpServletRequest request, String email, String password) throws Exception{
        UserBean user = null;
        
		try {
			user = this.userDAO.retrieveByEmail(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // check if user is registered in the db
        if (user == null) {
            throw new Exception("Invalid user email!");
        }
        // check if the password is correct
        if (user.getPassword().equals(password)) {
            request.getSession().setAttribute("user", user);
        }
    }

    public String logOut(HttpServletRequest request) {
    	if (request.getSession().getAttribute("user") != null) {
    		return "Invalid log out request";
    	}
    	else
    	{
        request.getSession().setAttribute("user", null);
        return "Successfully logged out";
    	}
    	
    }

    public void registerUser(String fName, String lName, String email, String password, HttpServletRequest request) {
        try {
  
			this.userDAO.register(fName, lName, email, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
 
    }


    /*
     * Cart
     */

    public void addToCart(String bid, HttpServletRequest request) {
    	HashMap<String, Integer> cart = this.getCart(request);
    		int quantity = 1;
    	if (cart.containsKey(bid)) {
    		quantity = cart.get(bid) + 1;
    	}
    	cart.put(bid, quantity);
    }

    public void removeFromCart(String bid, HttpServletRequest request) {
        HashMap<String, Integer> cart = this.getCart(request);
        cart.remove(bid);
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
    
    public double getTotalPrice (HttpServletRequest request) {
    	HashMap<String, Integer> cart = (HashMap<String, Integer>) request.getSession().getAttribute("cart");
    	
    	double total = 0;
    	
    	Iterator iterator = cart.entrySet().iterator();
    	
    	while (iterator.hasNext()) {
    		 Map.Entry mapElement = (Map.Entry)iterator.next();
    		 double bookPrice = this.getBookById(mapElement.getKey().toString()).getPrice();
    		 int quantity = (int)mapElement.getValue();
    		 total += bookPrice * quantity;
    	}
    	return total;
    }

    /*
     * Book
     */
    public ArrayList<BookBean> getBooksByCategory(String category) {
    	
    	try {
			return this.bookDAO.retrieveBooksByCategory(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    public ArrayList<BookBean> getAllBooks() {
    	try {
			return this.bookDAO.retrieveAllBooks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    public BookBean getBookById(String bid) {
    	try {
			return this.bookDAO.retrieveBookByID(bid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    // search books by author name or title //
    public ArrayList<BookBean> searchBooks(String query) {
    	try {
			return this.bookDAO.searchBooksByTitleOrAuthor(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
    public void addReview(String bid, int uid, double rating, String subject, String description)  {
    	try {
			this.reviewDAO.addReview(bid, uid, rating, subject, description);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    }

    public ArrayList<ReviewBean> getReview(String bid){
    	
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
        BookBean book = this.bookDAO.retrieveBookByID(bid);
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
    
    public void addAddress(HttpServletRequest request, String street, String province, String country, String zip, String phone ) {
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
    
    /*
     * Payments
     */

   
    
}
