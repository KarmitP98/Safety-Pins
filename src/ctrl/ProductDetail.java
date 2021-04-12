package ctrl;

import bean.BookBean;
import bean.ReviewBean;
import model.MainModel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "product-detail", value = "/product-detail")
public class ProductDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
        response.setContentType("text/html");
        String result = "<%@ page contentType=\"text/html;charset=UTF-8\" language=\"java\" %>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <link href=\"styles.css\" rel=\"stylesheet\"/>\n" +
                "    <link\n" +
                "            href=\"https://fonts.googleapis.com/icon?family=Material+Icons\"\n" +
                "            rel=\"stylesheet\"\n" +
                "    />\n" +
                "    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script>\n" +
                "    <title>Product Detail</title>\n" +
                "</head>\n" +
                "<body>";

        String bid = request.getParameter("bid");
        
        // adds visitevent viewtype
        MainModel.getInstance().addVisitEvent(bid, "VIEW");


        // Fetch Book Information
        BookBean bookBean = MainModel.getInstance().getBookById(bid);
        // Fetch Reviews for the Book
        ArrayList<ReviewBean> reviews = MainModel.getInstance().getReviews(bid);

        //Loop through all reviews get avg of ratings
        
        double rating = 0;
        for (ReviewBean review : reviews) {
        	
        	
        	rating += review.getRating();
        }
        rating = rating / reviews.size();

        // Print the entire page
        result += "<div class = 'product-detail' id=\"product-detail\">\n" +
                "    <div class = 'detail-card'>\n" +
                "        <div class = 'product-area'>\n" +
                "            <div class = 'product-image'>\n" +
                "                <img alt = 'Cover 2' src = '" + bookBean.getPicture() + "'>\n" +
                "            </div>\n" +
                "            <div class = 'product-body'>\n" +
                "                <div class = 'product-title'>" + bookBean.getTitle() + "</div>\n" +
                "                <div class = 'product-subtitle'>" + bookBean.getAuthor() + "</div>\n" +
                "                <div class = 'product-rating-area'>\n" +
                "                    Ratings: <span class = 'product-rating'>" + rating + " / 5</span>\n" +
                "                    <span class = 'product-reviews'>(" + reviews.size() + " Reviews)</span>\n" +
                "                </div>\n" +
                "                <div class = 'product-description'>" + bookBean.getDescription() +
                "                </div>\n" +
                "                <div class = 'price'>\n" +
                "                    US $ " + bookBean.getPrice() + "\n" +
                "                </div><br>\n" +
                "                <button class=\"button button-primary\" onclick=\"addItemToCart(" + bookBean.getBid() + ")\">Add to Cart</button>\n" +
                "<br><a href='home.jsp'><button class = 'button button-primary'>Back</button></a>"+
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class = 'review-area'>\n" +
                "            <div class = 'review-title'>Reviews</div>\n" +
                "            <div class = 'list-partition'></div>\n" +
                "\n" +
                "            <div class = 'review-section'>\n" +
                "                <div class = 'review-header'>\n" +
                "                    <div class = 'reviews'>" + rating + " / 5</div>\n" +
                "                </div>\n" +
                "\n" +
                "                <div class = 'center-line'></div>\n" +
                "\n" +
                "                <div class=\"review-list\">\n";

        for (ReviewBean review : reviews) {
            result += getReview(review);
        }

        result += "                </div>\n" +
                "            </div>\n" +
                "\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>" +
                "<script>\n" +
                "    function addItemToCart(bid) {\n" +
                "   console.log(bid);       " +
                " $.ajax({\n" +
                "            type: 'get',\n" +
                "            url: 'add-item',\n" +
                "            data: {bid: bid}\n" +
                "        })\n" +
                "    }\n" +
                "</script>\n" +
                "</body>\n" +
                "</html>"    ;

        response.getWriter().write(result);
    }

    private String getReview(ReviewBean reviewBean) {
        return "                    <div class = 'review'>\n" +
                "                        <div class = 'review-header'>\n" +
                "                            <div class = 'review-title'>\n" +
                "                                <h1>" + reviewBean.getSubject() + "</h1>\n" +
                "                            </div>\n" +
                "                            <div class = 'review-subtitle'>\n" +
                "                                <span class = 'name'>" + reviewBean.getUserID() + "</span>\n" +
                "                                <span class = 'rating'>" + reviewBean.getRating() + " / 5</span>\n" +
                "                            </div>\n" +
                "                        </div>\n" +
                "                        <div class = 'review-body'>\n" +
                "                            " + reviewBean.getDescription() + "" +
                "                        </div>\n" +
                "                    </div>\n";
    }
}
