package ctrl;

import bean.BookBean;
import model.MainModel;

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
 * Servlet implementation class Payment
 */
@WebServlet("/order-price/*")
public class OrderPrice extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderPrice() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        HashMap<BookBean, Integer> cart = MainModel.getInstance().getCart(request);
        int items = cart.size();
        double totalPrice = 0;

        for (Map.Entry<BookBean, Integer> item : cart.entrySet()) {
            totalPrice += item.getKey().getPrice() * item.getValue();
        }

        DecimalFormat df = new DecimalFormat("####0.00");
        response.getWriter().write("<b>Subtotal</b> (" + items + " Items): <b>$ " + df.format(totalPrice) + "</b>");

    }

}
