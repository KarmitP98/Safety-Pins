package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MainModel;

/**
 * Servlet implementation class SignUp
 */
@WebServlet("/SignUp/*")
public class SignUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MainModel model;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() {
		model = MainModel.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/Register.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String fName = request.getParameter("fName");
		String lName = request.getParameter("lName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (fName.length() > 0 && lName.length() > 0 && email.length() > 0 && password.length() > 0) {
			int registered = model.registerUser(fName, lName, email, password, request);
			
			System.out.println(registered);
		}
		
<<<<<<< HEAD
    	String fName = request.getParameter("fName");
    	String lName = request.getParameter("lName");
    	String email = request.getParameter("email");
    	String password = request.getParameter("password");
       	model.registerUser(fName, lName, email, password, request);
    	
    	request.getRequestDispatcher("Home.jspx").forward(request, response);
       	
      
=======
>>>>>>> c5b715d1331037741238c02d489a54ab23a42822
	}

}
