package ctrl;

import model.MainModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "add-item", value = "/add-item")
public class additem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	//add visitevent CART TYPE
//    	MainModel.getInstance().addVisitEvent(request.getParameter("bid"), "CART");
    	
        MainModel.getInstance().addToCart(request.getParameter("bid"), request);
    }
}
