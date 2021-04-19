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
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainModel {

    private static MainModel instance;
    private UserDAO userDAO;
    private BookDAO bookDAO;
    private CardDAO cardDAO;
    private PODAO poDAO;
    private VisitEventDAO visitEventDAO;
    private ReviewDAO reviewDAO;
    private AddressDAO addressDAO;
    private CounterDAO counterDAO;
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

            // If Visitor || Customer
            // deleted / for eclipse
            if (user.getUserType().equals("Customer"))
                response.sendRedirect("dashboard.jsp");

                // If Admin
                // Redirect to report-page.html

                // deleted / for eclipse
            else if (user.getUserType().equals("Administrator"))


                response.sendRedirect("/report-page.jsp");

                // If Partner
                // Redirect to the rest service page
            else {
                //redirect to home temporarily
                response.sendRedirect("dashboard.jsp");
            }


        } else {
            // deleted / for eclipse
            response.sendRedirect("login.html");
        }
    }

    public void logOut(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().removeAttribute("user");
        try {
            // deleted / for eclipse
            response.sendRedirect("login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public void registerUser(String fName, String lName, String email, String password, HttpServletRequest request, HttpServletResponse response) {

        try {
            int registered = this.userDAO.register(fName, lName, email, password);
            if (registered == 1) {
                UserBean userBean = this.userDAO.fetchUserByEmail(email);
                request.getSession().setAttribute("user", userBean);
                this.counterDAO.addCounter(userBean.getUserID());
                System.out.println("A new user has been registered!");


                request.getSession().setAttribute("userSigningUp", userBean);

                // deleted / for eclipse
//                response.sendRedirect("add-info.jsp");
                response.sendRedirect("dashboard.jsp");
            } else
                // deleted / for eclipse
                response.sendRedirect("signup.html");
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

    public boolean addToCart(String bid, HttpServletRequest request) {

        BookBean book = this.getBookById(bid);

        HashMap<BookBean, Integer> cart = this.getCart(request);

        if (cart.containsKey(book)) {
            cart.replace(book, cart.get(book) + 1);
            request.getSession().setAttribute("cart", cart);
            return false;
        }
        cart.put(book, 1);
        request.getSession().setAttribute("cart", cart);
        return true;
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
            System.out.println("MainModel.getAllBooks");
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

        BookBean book = this.bookDAO.retrieveByBookId(bid);
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
    public String getOrdersByPartNumber(String bid) throws SQLException, JAXBException, IOException {

        String result = "";
        ArrayList<POItemBean> poItems;

        poItems = this.poDAO.retrievePOItemsByBookId(bid);
        ArrayList<OrderWrapper> wrapperList = new ArrayList<OrderWrapper>();
        if (poItems == null) return "No orders for the productId available";
        for (POItemBean item : poItems) {
            int pid = item.getId();
            POBean po = this.getPObyId(pid);
            ArrayList<POItemBean> itemList = this.getPOItemsbyPOId(pid);
            wrapperList.add(new OrderWrapper(po.getDate(), this.addressDAO.retrieveAddressByUserId(po.getUserID()), this.addressDAO.retrieveAddressByUserId(po.getUserID()), itemList, pid, po.getUserID()));
        }

        for (OrderWrapper wrapper : wrapperList) {
            JAXBContext jc = JAXBContext.newInstance(wrapper.getClass());
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

            StringWriter sw = new StringWriter();
            sw.write("\n");
            marshaller.marshal(wrapper, new StreamResult(sw));

            result += sw.toString();
        }

        return result;
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

        if (user == null)
            user = (UserBean) request.getSession().getAttribute("userSigningUp");


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

    public void addPOandItems(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        UserBean user = (UserBean) request.getSession().getAttribute("user");
        HashMap<BookBean, Integer> cart = (HashMap<BookBean, Integer>) request.getSession().getAttribute("cart");
        AddressBean address = this.getAddress(request);

        System.out.println("address = " + address);

        String status = "Ordered";
        if (this.getCounter(request) % 3 == 0) {
            status = "Denied";
        }
        DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());


        //adds a new purchase order
        int pid = addPO(user.getUserID(), status, address.getId(), date);

        //adds POItems for each item in the cart
        for (Map.Entry<BookBean, Integer> item : cart.entrySet()) {
            this.addPOItem(pid, item.getKey().getBid(), item.getKey().getPrice(), item.getValue());
            // add visitevent PURCHASE TYPE
//            this.addVisitEvent(item.getKey().getBid(), date);
        }

        this.incrementCounter(request);

        try {
            if (status.equals("Ordered")) {
                response.sendRedirect("/shipped.jsp");
            } else
                response.sendRedirect("/error.jsp");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
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

    public void addVisitEvent(String bid, String type) {

        DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        Calendar cal = Calendar.getInstance();
        String day = dateFormat.format(cal.getTime());

        System.out.println("day = " + day);
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


    public void addCard(HttpServletRequest request, String cardNumber, String cvc, String expiryDate) {
        UserBean userBean = (UserBean) request.getSession().getAttribute("user");

        if (userBean == null)
            userBean = (UserBean) request.getSession().getAttribute("userSigningUp");

        try {
            this.cardDAO.addCard(userBean.getUserID(), cardNumber, cvc, expiryDate);
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
