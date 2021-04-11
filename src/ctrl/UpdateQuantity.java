package ctrl;

import bean.BookBean;
import model.MainModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "update-qty", value = "/update-qty")
public class UpdateQuantity extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int number = Integer.parseInt(request.getParameter("number"));
        String bid = request.getParameter("bid");

        HashMap<BookBean, Integer> cart = (HashMap<BookBean, Integer>) request.getSession().getAttribute("cart");

        boolean bidToRemove = false;
        request.getSession().setAttribute("cart", cart);

        BookBean remove = null;

        for (Map.Entry<BookBean, Integer> entry : cart.entrySet()) {
            BookBean book = (BookBean) entry.getKey();
            if (book.getBid().equals(bid)) {
                int current = entry.getValue() + number;
                if (current <= 0) {
                    bidToRemove = true;
                    remove = entry.getKey();
                } else {
                    entry.setValue(current);
                }
            }
        }

        if(bidToRemove)
            cart.remove(remove);

        request.getSession().setAttribute("cart", cart);
//
//        if (bidToRemove)
//            MainModel.getInstance().removeFromCart(bid, request);

        if (cart.size() <= 0)
            MainModel.getInstance().emptyCart(request);

    }
}
