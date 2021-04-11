<%@ page import="bean.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: Karmit
  Date: 11-Apr.-2021
  Time: 11:16 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="styles.css" rel="stylesheet"/>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet"/>
    <script src="search.js" type="text/javascript"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <title>Dashboard</title>
</head>
<body>
<nav>
    <% UserBean userBean = (UserBean) request.getSession().getAttribute("user");%>
    <div class="navbar">
        <a href="home.jsp" id="logo"> <img alt="Red Logo"
                                            src="assets/logo-red-bg.png"/>
        </a>
        <fieldset>
            <input id="book" placeholder="Explore our catalogue..." type="text"/>
            <span class="material-icons" onclick="searchByName()">search</span>
        </fieldset>
        <div class="options-area">
            <a href="cart.jsp">
                <div class="cart-btn">
						<span class="material-icons">shopping_cart
						</span>
                </div>
            </a> <span class="center-line"></span>
            <a href="<%= userBean!= null ? "/logout":"login.html"%>">
            <div class="logout-btn">
                <span class="material-icons"><%= userBean != null ? "logout" : "login"%></span>
            </div>
            </a>
        </div>
    </div>
</nav>
<div class="main-content-area">
    <div class="category">
        <div class="title">Category</div>
        <form method="post" style="width:50%;">
            <select id="category"
                    name="category">
                <option value="all">All</option>
                <option value="fiction">Fiction</option>
                <option value="science">Science</option>
                <option value="engineering">Engineering</option>
                <option value="maths">Maths</option>
                <option value="modern">Modern</option>
                <option value="kids">Kids</option>
            </select> <br>
            <br>
            <button class='button button-primary' onclick="searchByCategory()" type="button">Filter</button>
        </form>
    </div>
</div>
<div class="catalogue" id="catalogue">

</div>
</body>
</html>
