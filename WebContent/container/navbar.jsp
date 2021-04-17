<%@ page import="bean.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/navbar.scss">
    <title>Navbar</title>
</head>
<body>
<nav>
    <div class="navbar">
        <img src="${pageContext.request.contextPath}/assets/logo-red-bg.png"
             alt="Red Logo"
        />
        <% if (request.getParameter("from").equals("Home")) {%>
        <form action="" method="post">
            <fieldset>
                <div class="material-icons"
                     onclick="searchByName()">
                    search
                </div>
                <input type="text"
                       name="search"
                       id="search"
                       placeholder="Explore our catalogue...">
            </fieldset>
        </form>
        <%}%>
        <div class="options-area">
            <div class="cart-btn">
						<span class="material-icons">shopping_cart
						</span>
            </div>
            <span class="center-line"></span>
            <div class="logout-btn">
                <span class="material-icons">login</span>
            </div>
        </div>
    </div>
</nav>
</body>
</html>
