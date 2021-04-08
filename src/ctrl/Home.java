package ctrl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import dao.BookDAO;
import model.MainModel;

import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
   
    private static final long serialVersionUID = 1L;

    private MainModel model;
    private String message;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Home() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	
    	model = MainModel.getInstance();
    }
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.getWriter().append("Served at: ").append(request.getContextPath());
        
        if (request.getParameter("signUp") != null) {
            //getParameters(userInfo)
            //model.register()
        	String fName = request.getParameter("fName");
        	String lName = request.getParameter("lName");
        	String email = request.getParameter("email");
        	String password = request.getParameter("password");
        	
        	model.registerUser(fName, lName, email, password, request);
        }

        if (request.getParameter("signIn") != null) {
        	
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            try {
				model.logIn(request, email, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            
        }
        if (request.getParameter("logOut") != null) {
            //
            //model.signOut
        	message = model.logOut(request);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
