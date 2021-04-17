<%@ page import="bean.UserBean" %>
<%@ page import="model.MainModel" %>
<%@ page import="bean.BookBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="ctrl.Book" %><%--
  Created by IntelliJ IDEA.
  User: karmi
  Date: 2021-04-11
  Time: 11:18 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../../styles/styles.css" rel="stylesheet"/>
    <link
            href="https://fonts.googleapis.com/icon?family=Material+Icons"
            rel="stylesheet"
    />
    <title>Report Page</title>
</head>
<body>
<% UserBean user = (UserBean) session.getAttribute("user");
    if (user == null)
        response.sendRedirect("login");
    ArrayList<BookBean> books = MainModel.getInstance().getTop10();
    String result = "";
%>
<nav>
    <div class="navbar">
        <div id="logo">
            <img alt="Red Logo" src="../../assets/logo-red-bg.png"/>
        </div>
        <div class="options-area">
            <a href="logout">
                <div class="logout-btn">
                    <span class="material-icons">logout</span>
                </div>
            </a>
        </div>
    </div>
</nav>
<div class="main-content-area" style="flex-flow: column; justify-content: center; align-items: center;">
    <button class="button button-primary" onclick="getTop10()">Run Report</button>
    <div id="content-area">
        <%
            result += "<table>\n" +
                    "            <tr>\n" +
                    "                <th>Book ID</th>\n" +
                    "                <th>Book Name</th>\n" +
                    "                <th>Author</th>\n" +
                    "                <th>Sold</th>\n" +
                    "            </tr>";
            for (BookBean bookBean : books) {
                result += "<tr>\n" +
                        "                <td>" + bookBean.getBid() + "</td>\n" +
                        "                <td>" + bookBean.getTitle() + "</td>\n" +
                        "                <td>" + bookBean.getAuthor() + "</td>\n" +
                        "                <td>" + bookBean.getQuantitySold() + "</td>\n" +
                        "            </tr>";
            }
            ;

            result += "</table>";

            out.print(result);
        %>
        <%--        <table>--%>
        <%--            <tr>--%>
        <%--                <th>Number</th>--%>
        <%--                <th>Book ID</th>--%>
        <%--                <th>Book Name</th>--%>
        <%--                <th>Author</th>--%>
        <%--                <th>Sold</th>--%>
        <%--            </tr>--%>
        <%--            <tr>--%>
        <%--                <td>1</td>--%>
        <%--                <td>BID</td>--%>
        <%--                <td>Name</td>--%>
        <%--                <td>1</td>--%>
        <%--                <td>1</td>--%>
        <%--            </tr>--%>
        <%--            <tr>--%>
        <%--                <td>1</td>--%>
        <%--                <td>BID</td>--%>
        <%--                <td>Name</td>--%>
        <%--                <td>1</td>--%>
        <%--                <td>1</td>--%>
        <%--            </tr>--%>
        <%--            <tr>--%>
        <%--                <td>1</td>--%>
        <%--                <td>BID</td>--%>
        <%--                <td>Name</td>--%>
        <%--                <td>1</td>--%>
        <%--                <td>1</td>--%>
        <%--            </tr>--%>
        <%--            <tr>--%>
        <%--                <td>1</td>--%>
        <%--                <td>BID</td>--%>
        <%--                <td>Name</td>--%>
        <%--                <td>1</td>--%>
        <%--                <td>1</td>--%>
        <%--            </tr>--%>
        <%--            <tr>--%>
        <%--                <td>1</td>--%>
        <%--                <td>BID</td>--%>
        <%--                <td>Name</td>--%>
        <%--                <td>1</td>--%>
        <%--                <td>1</td>--%>
        <%--            </tr>--%>
        <%--        </table>--%>
    </div>
</div>
<script>
    function getTop10() {
        $.ajax({
            type: 'get',
            url: 'top',
            success: function (res) {
                document.getElementById("content-area").innerHTML = res;
            }
        })
    }
</script>
</body>
</html>
