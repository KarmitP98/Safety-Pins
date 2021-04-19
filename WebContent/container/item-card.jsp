<%@ page import="model.MainModel" %><%--
  Created by IntelliJ IDEA.
  User: Karmit
  Date: 17-Apr.-2021
  Time: 05:05 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/item-card.scss">
    <script src="${pageContext.request.contextPath}/js/func.js" type="application/javascript"></script>
    <title>Item Card</title>
</head>
<body>
<%
    double avgRating = 4.21;
//    String rating = MainModel.getInstance().getAvgRating(bid);

%>
<div class="book-card">
    <div class="card-image">
        <img alt="Book Cover 1" src='<%=request.getParameter("imageURL")%>'/>
    </div>
    <div class="card-body">
        <div class="card-title"><%=request.getParameter("title")%>
        </div>
        <div class="card-subtitle"><%=request.getParameter("author")%>
        </div>
        <div class="price">
            <p>$ <%=request.getParameter("price")%>
            </p>
        </div>
        <br>
        <div class="ratings">
            <p><b>Ratings:</b> <%=avgRating%> / 5
            </p>
        </div>
    </div>
    <div class="card-action">
        <a href='<%="item-detail?bid=" + request.getParameter("bid")%>'>
            <div class="material-icons" id="open">open_in_full</div>
        </a>
        <div class="material-icons" id="cart" onclick="addToCart('<%=request.getParameter("bid")%>')">
            add_shopping_cart
        </div>
    </div>
</div>
</body>
</html>
