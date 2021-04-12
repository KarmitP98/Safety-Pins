package ctrl;

import bean.AddressBean;
import bean.CardBean;
import com.google.gson.Gson;
import model.MainModel;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "User", value = "/user")
public class User extends HttpServlet {

    private MainModel mainModel;

    @Override
    public void init(ServletConfig config) throws ServletException {
        mainModel = MainModel.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        boolean getAddress = request.getParameter("address").length() > 0;
        boolean getPayment = request.getParameter("payment").length() > 0;

        String uid = (String) request.getSession().getAttribute("uid");

        AddressBean addressBean = mainModel.getAddress(request);
        CardBean cardBean = mainModel.getCard(request);

        String json = new Gson().toJson(addressBean.toString() + "" + cardBean.toString());
        response.getWriter().write(json);
        response.getWriter().flush();

    }
}
