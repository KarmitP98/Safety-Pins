<%@ page import="bean.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: Karmit
  Date: 11-Apr.-2021
  Time: 10:24 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="styles/styles.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="js/cart.js" type="text/javascript"></script>
    <title>Shopping Cart</title>
</head>
<body>
<% UserBean userBean = (UserBean) request.getSession().getAttribute("user");%>
<jsp:include page="/container/navbar.jsp">
    <jsp:param name="from" value="Cart"/>
</jsp:include>

</body>
</html>
