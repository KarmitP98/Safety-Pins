package ctrl;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Book
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/book" })
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Book() {
		super();
		System.out.println("Hello World");
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setContentType("html/text");
		System.out.println("Here");
		response.getWriter()
				.write("<div class=\"book-card\">\r\n" + "			<div class=\"card-image\">\r\n"
						+ "				<img alt=\"Book Cover 1\" src=\"assets/book-cover-1.jpg\" />\r\n"
						+ "			</div>\r\n" + "			<div class=\"card-body\">\r\n"
						+ "				<div class=\"card-title\">Title</div>\r\n"
						+ "				<div class=\"card-subtitle\">Subtitle</div>\r\n"
						+ "				<div class=\"price\">\r\n" + "					<p>$ 21.99</p>\r\n"
						+ "				</div>\r\n" + "			</div>\r\n" + "			<div class=\"card-action\">\r\n"
						+ "				<div class=\"book-rating\">\r\n" + "					<p>4.5 / 5</p>\r\n"
						+ "				</div>\r\n" + "				<div class=\"card-action-list\">\r\n"
						+ "					<span class=\"material-icons\" id=\"open\">launch</span> &nbsp; <span\r\n"
						+ "						class=\"material-icons\" id=\"bag\">shopping_bag</span>\r\n"
						+ "				</div>\r\n" + "			</div>\r\n" + "		</div>");
	}

}
