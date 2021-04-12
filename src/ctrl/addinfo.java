package ctrl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MainModel;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "add-info", value = "/info")
public class addinfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = request.getParameter("addressLine");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String zip = request.getParameter("zip");
        String card = request.getParameter("card");
        String cvs = request.getParameter("cvs");
        String expiry = request.getParameter("expiry");

        System.out.println("expiry = " + expiry);

        // Add Info
        // This info to address table and card table
        // Phone number empty
        
        MainModel model = MainModel.getInstance();
        model.addAddress(request, address, state, country, zip, "");
        model.addCard(request, card, cvs, Date.valueOf(expiry));
        
        if (request.getSession().getAttribute("userSigningUp") != null) {
        request.getSession().setAttribute("userSigningUp", null); 
        response.sendRedirect("home.jsp");
        }
        else {
        	response.sendRedirect("reviewOrder.jsp");
        }
        
    }
}
