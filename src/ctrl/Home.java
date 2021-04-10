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
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Servlet implementation class Home
 */
@WebServlet("/Home/*")
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
    	
          
        if (request.getParameter("signUp") != null) {
          
       	request.getRequestDispatcher("/Register.jspx").forward(request, response);
        	
        }
        else if (request.getParameter("search")!= null) {
        	String query = request.getParameter("query");
        	ArrayList<BookBean> list = model.searchBooks(query);
        	
        	Writer writer = response.getWriter();
        	
        	writer.append(list.get(0).getAuthor());
        }

        else if (request.getParameter("logIn") != null) {     	

        	request.getRequestDispatcher("/LogIn.jspx").forward(request, response);
        
            
        }
       
        else if (request.getParameter("logOut") != null) {
            //
            //model.signOut
        	message = model.logOut(request);
        }
       
        else if (request.getParameter("browse") != null) {
        	request.getRequestDispatcher("/Catalogue.jspx").forward(request, response);
        }
       
        else if (request.getParameter("cart") != null) {
        	request.getRequestDispatcher("/Cart.jspx").forward(request, response);
        }
        else
        {   try {
			model.test();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
        	request.getRequestDispatcher("/Home.jspx").forward(request, response);
        	
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
