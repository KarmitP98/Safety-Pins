package ctrl;

import bean.UserBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "user-load", value = "/user-load")
public class UserLoad extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        UserBean userBean = (UserBean) request.getSession().getAttribute("user");
        System.out.println("userBean = " + userBean);
        if (userBean != null) {
            response.getWriter().write(userBean.getfName() + " " + userBean.getlName());
        } else {
            response.getWriter().write("User not logged in!");
        }
    }
}
