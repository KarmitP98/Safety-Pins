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
import java.util.ArrayList;

/**
 * Servlet implementation class Book
 */
@WebServlet(asyncSupported = true, urlPatterns = {"/book"})
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
     * response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        response.setContentType("html/text");

        String name = request.getParameter("name");
        String category = request.getParameter("category");


        ArrayList<BookBean> books = new ArrayList<>();

        if (name.equals("") && category.equals("all")) {
            books = model.getAllBooks();
        }
        if (name.trim().length() > 0 && category.equals("all")) {
            books = model.searchBooks(name);
        }
        if (name.trim().length() > 0 && !category.equals("all")) {
            books = model.fetchBookbyNameandCategory(name, category);
        }
        if (name.trim().length() == 0 && !category.equals("all")) {
            books = model.getBooksByCategory(category);
        }

        StringBuilder result = new StringBuilder();

        for (BookBean book : books) {
            System.out.println("book = " + book.toString());
            result.append(getBookCard(book));
        }

        response.getWriter().write(result.toString());
    }

    private String getBookCard(BookBean book) {
        return "<div class=\"book-card\">\r\n" + "			<div class=\"card-image\">\r\n"
                + "				<img alt=\"Book Cover 1\" src='" + book.getPicture() + "' />\r\n"
                + "			</div>\r\n" + "			<div class=\"card-body\">\r\n"
                + "				<div class=\"card-title\">" + book.getTitle() + "</div>\r\n"
                + "				<div class=\"card-subtitle\">" + book.getAuthor() + "</div>\r\n"
                + "				<div class=\"price\">\r\n" + "					<p>$ " + book.getPrice() + "</p>\r\n"
                + "				</div>\r\n" + "			</div>\r\n" + "			<div class=\"card-action\">\r\n"
                + "				<div class=\"book-rating\">\r\n" + "					<p>4.5 / 5</p>\r\n"
                + "				</div>\r\n" + "				<div class=\"card-action-list\">\r\n"
                + "					<span class=\"material-icons\" id=\"open\">launch</span> &nbsp; <span\r\n"
                + "						class=\"material-icons\" id=\"bag\" onclick='addItemToCart(\"" + book.getBid() + "\")'>shopping_bag</span>\r\n"
                + "				</div>\r\n" + "			</div>\r\n" + "		</div>";
    }

}
