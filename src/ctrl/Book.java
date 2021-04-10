package ctrl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import model.MainModel;

/**
 * Servlet implementation class Book
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/book" })
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MainModel model;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Book() {
		super();
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

		String name = request.getParameter("name");
		String category = request.getParameter("category");
		String bid = request.getParameter("bid");

		ArrayList<BookBean> books = new ArrayList<>();
		
		System.out.println(name);
		System.out.println(category);

		if (category.equals("all")) {
			books = model.getAllBooks();
		}
		if(name.length() > 0)
		{
			books = model.searchBooks(name);
		}

		String result = "";

		for (BookBean book : books) {
			result += getBookCard(book);
		}

		// Sample output
		response.getWriter().write(result);
	}

	private String getBookCard(BookBean book) {
		return "<div class=\"book-card\">\r\n" + "			<div class=\"card-image\">\r\n"
				+ "				<img alt=\"Book Cover 1\" src=\'" + book.getPicture() + "\' />\r\n"
				+ "			</div>\r\n" + "			<div class=\"card-body\">\r\n"
				+ "				<div class=\"card-title\">" + book.getTitle() + "</div>\r\n"
				+ "				<div class=\"card-subtitle\">" + book.getAuthor() + "</div>\r\n"
				+ "				<div class=\"price\">\r\n" + "					<p>$ " + book.getPrice() + "</p>\r\n"
				+ "				</div>\r\n" + "			</div>\r\n" + "			<div class=\"card-action\">\r\n"
				+ "				<div class=\"book-rating\">\r\n" + "					<p>4.5 / 5</p>\r\n"
				+ "				</div>\r\n" + "				<div class=\"card-action-list\">\r\n"
				+ "					<span class=\"material-icons\" id=\"open\">launch</span> &nbsp; <span\r\n"
				+ "						class=\"material-icons\" id=\"bag\">shopping_bag</span>\r\n"
				+ "				</div>\r\n" + "			</div>\r\n" + "		</div>";
	}

}
