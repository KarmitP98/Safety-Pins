package ctrl;

import bean.BookBean;
import model.MainModel;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Servlet implementation class ShoppingCart
 */
@WebServlet("/cart")
public class ShoppingCart extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MainModel model;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShoppingCart() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init(ServletConfig config) throws ServletException {
        // TODO Auto-generated method stub
        model = MainModel.getInstance();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
//		doGet(request, response);

        HashMap<BookBean, Integer> cart = model.getCart(request);
        StringBuilder result = new StringBuilder();

        for (Map.Entry<BookBean, Integer> book : cart.entrySet()) {
            result.append(this.getItem(book.getKey(), book.getValue()));
        }

        response.getWriter().write(cart.size() > 0 ? result.toString() : "There are no items in you cart. <br><br>" +
                "<h1><a href='home.jsp'>Browse our catalogue</a>");
    }

    private String getItem(BookBean book, int number) {
        DecimalFormat df = new DecimalFormat("####0.00");
        return "<div class=\"order-detail-item\">\n" +
                "                <div class=\"list-partition\"></div>\n" +
                "                <div class=\"order-item\">\n" +
                "                    <div class=\"item-image\">\n" +
                "                        <img alt=\"Book Cover 6\" src=\"" + book.getPicture() + "\"/>\n" +
                "                    </div>\n" +
                "                    <div class=\"item-body\">\n" +
                "                        <div class=\"item-header\">\n" +
                "                            <div class=\"item-title\">" + book.getTitle() + "</div>\n" +
                "                            <p>Paperback</p>\n" +
                "                            <span><span class=\"material-icons\">bookmark</span> Backed\n" +
                "\t\t\t\t\t\t\t\tby Safety ++. </span>\n" +
                "                        </div>\n" +
                "                        <div class=\"item-details\">\n" +
                "                            <div class=\"quantity\">\n" +
                "                                Qty:&nbsp; <input max=\"10\" min=\"1\" name=\"qty\" placeholder=\"1\"\n" +
                "                                                  type=\"number\" value=\"" + number + "\"/> &nbsp;&nbsp;<span class=\"add-sub\">\n" +
                "\t\t\t\t\t\t\t\t\t<button class=\"button\" onclick=\"updateQuantity('" + book.getBid() + "',1)\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<span class=\"material-icons sub-item\">expand_less</span>\n" +
                "\t\t\t\t\t\t\t\t\t</button> <span class=\"center-line\"></span>\n" +
                "\t\t\t\t\t\t\t\t\t<button class=\"button\" onclick=\"updateQuantity('" + book.getBid() + "',-1)\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<span class=\"material-icons add-item\">expand_more</span>\n" +
                "\t\t\t\t\t\t\t\t\t</button><span class=\"center-line\"></span>\n" +
                "<button class=\"button\" onclick=\"updateQuantity('" + book.getBid() + "',-100000)\">\n" +
                "\t\t\t\t\t\t\t\t\t\t<span class=\"material-icons add-item\">delete</span>\n" +
                "\t\t\t\t\t\t\t\t\t</button>\n" +
                "\t\t\t\t\t\t\t\t</span>\n" +
                "                            </div>\n" +
                "                            <div class=\"price\">\n" +
                "                                Price: <span class=\"pvalue\">$ " + df.format(book.getPrice()) + "</span>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>";
    }

}
