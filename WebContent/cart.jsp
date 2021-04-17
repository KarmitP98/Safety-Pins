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
<nav>
    <div class="navbar">
        <a href="dashboard.jsp" id="logo">
            <img alt="Red Logo" src="assets/logo-red-bg.png"/>
        </a>
        <div class="options-area">
            <a href="dashboard.jsp">
                <div class="cart-btn">
                    <span class="material-icons">home </span>
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
    <div class="order-details" id="order-details" style="width: 50%;">
        <div class="title">Shopping Cart</div>
        <a class="remove-link">Remove all items</a>
        <div id="order-items">

            <div class="order-detail-item">
                <div class="list-partition"></div>
                <div class="order-item">
                    <div class="item-image">
                        <img alt="Book Cover 6" src="assets/book-cover-16.jpg"/>
                    </div>
                    <div class="item-body">
                        <div class="item-header">
                            <div class="item-title">The Outsider</div>
                            <p>Paperback</p>
                            <span><span class="material-icons">bookmark</span> Backed
								by Safety ++. </span>
                        </div>
                        <div class="item-details">
                            <div class="quantity">
                                Qty:&nbsp; <input max="10" min="1" name="qty" placeholder="1"
                                                  type="number"/> &nbsp;&nbsp;<span class="add-sub">
									<button class="button">
										<span class="material-icons add-item">expand_less</span>
									</button> <span class="center-line"></span>
									<button class="button">
										<span class="material-icons sub-item">expand_more</span>
									</button>
								</span>
                            </div>
                            <div class="price">
                                Price: <span class="pvalue">$ 12.99</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="order-detail-item">
                <div class="list-partition"></div>
                <div class="order-item">
                    <div class="item-image">
                        <img alt="Book Cover 6" src="assets/book-cover-16.jpg"/>
                    </div>
                    <div class="item-body">
                        <div class="item-header">
                            <div class="item-title">The Outsider</div>
                            <p>Paperback</p>
                            <span><span class="material-icons">bookmark</span> Backed
								by Safety ++. </span>
                        </div>
                        <div class="item-details">
                            <div class="quantity">
                                Qty:&nbsp; <input max="10" min="1" name="qty" placeholder="1"
                                                  type="number"/> &nbsp;&nbsp;<span class="add-sub">
									<button class="button">
										<span class="material-icons add-item">expand_less</span>
									</button> <span class="center-line"></span>
									<button class="button">
										<span class="material-icons sub-item">expand_more</span>
									</button>
								</span>
                            </div>
                            <div class="price">
                                Price: <span class="pvalue">$ 12.99</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="order-total">
        <div class="card-title" id="order-title">
            <b>Subtotal</b> (3 Items): <b>$ 131.93</b>
        </div>
        <div class="card-subtitle">Taxes will be calculated at checkout</div>
        <br/> <br/>
        <div class="card-body">
            <a href='<%= userBean!= null ? "reviewOrder.jsp":"login.html"%>'>
                <button class="button button-primary">
                    <%= userBean != null ? "Proceed to Checkout" : "Login to Continue"%>
                </button>
            </a>

        </div>
    </div>
</div>
</body>
</html>
