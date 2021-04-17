<%@ page import="bean.UserBean" %>
<%@ page import="bean.AddressBean" %>
<%@ page import="model.MainModel" %>
<%@ page import="bean.CardBean" %>
<%@ page import="sun.applet.Main" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.LocalDateTime" %><%--
  Created by IntelliJ IDEA.
  User: Karmit
  Date: 11-Apr.-2021
  Time: 11:07 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="styles/styles.css" rel="stylesheet"/>
    <link
            href="https://fonts.googleapis.com/icon?family=Material+Icons"
            rel="stylesheet"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/cart.js"></script>
    <script type="javascript" src="js/order-review.js"></script>
    <title>Review Order</title>
</head>
<body>
<% UserBean userBean = (UserBean) session.getAttribute("user");
    if (userBean == null) {
        response.sendRedirect("login.html");
    }
    AddressBean address = MainModel.getInstance().getAddress(request);
    if (address == null) {
        address = new AddressBean();
        address.setStreet("123 Playground Street");
        address.setProvince("LA");
        address.setZip("123 456");
        address.setCountry("USA");
        address.setPhone("+1-123-456-7890");
    }
    ;

    CardBean card = MainModel.getInstance().getCard(request);
    if (card == null) {
        card = new CardBean();
        card.setCardNumber("252551426780");
        card.setCvc("123");
    }
%>
<nav>
    <div class="navbar">
        <a href="dashboard.jsp">
            <div id="logo">
                <img alt="Red Logo" src="assets/logo-red-bg.png"/>
            </div>
        </a>
        <div class="options-area">
            <a href="dashboard.jsp">
                <div class="cart-btn">
                    <span class="material-icons">home</span>
                </div>
            </a>
            <span class="center-line"></span>
            <a href="<%= userBean!= null ? "/logout":"login.html"%>">
                <div class="logout-btn">
                    <span class="material-icons"><%= userBean != null ? "logout" : "login"%></span>
                </div>
            </a>
        </div>
    </div>
</nav>
<div class="main-content-area">
    <div class="center-content" style="width: 50%;">
        <div class="checkout-details">
            <div class="section">
                <div class="section-title">Shipping Address</div>
                <div class="section-body" id="address">
                    <p class="address"><%=address.getStreet()%>
                    </p>
                    <p class="city"><%=address.getProvince()%>, <%=address.getZip()%>
                    </p>
                    <p class="country"><%=address.getCountry()%>
                    </p>
                    <p class="phone">Phone: <%=address.getPhone()%>
                    </p>
                </div>
            </div>
            <div class="section">
                <div class="section-title">Choose a payment method</div>
                <div class="section-body">
                    <p class="card" id="card-info">
                        <span class="material-icons">payment</span>ending in
                        <%=card.getCardNumber().substring(card.getCardNumber().length() - 4)%>
                    </p>
                    <%--                    <a href="add-info.jsp">Change payment method</a>--%>
                </div>
            </div>
        </div>

        <div class="order-details">
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
                                <span
                                ><span class="material-icons">bookmark</span>
                    Backed by Safety ++.
                  </span>
                            </div>
                            <div class="item-details">
                                <div class="quantity">
                                    Qty:&nbsp;
                                    <input
                                            max="10"
                                            min="1"
                                            name="qty"
                                            placeholder="1"
                                            type="number"
                                    />
                                    &nbsp;&nbsp;<span class="add-sub">
                      <button class="button">
                        <span class="material-icons add-item">expand_less</span>
                      </button>
                      <span class="center-line"></span>
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
                                <span
                                ><span class="material-icons">bookmark</span>
                    Backed by Safety ++.
                  </span>
                            </div>
                            <div class="item-details">
                                <div class="quantity">
                                    Qty:&nbsp;
                                    <input
                                            max="10"
                                            min="1"
                                            name="qty"
                                            placeholder="1"
                                            type="number"
                                    />
                                    &nbsp;&nbsp;<span class="add-sub">
                      <button class="button">
                        <span class="material-icons add-item">expand_less</span>
                      </button>
                      <span class="center-line"></span>
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
                                <span
                                ><span class="material-icons">bookmark</span>
                    Backed by Safety ++.
                  </span>
                            </div>
                            <div class="item-details">
                                <div class="quantity">
                                    Qty:&nbsp;
                                    <input
                                            max="10"
                                            min="1"
                                            name="qty"
                                            placeholder="1"
                                            type="number"
                                    />
                                    &nbsp;&nbsp;<span class="add-sub">
                      <button class="button">
                        <span class="material-icons add-item">expand_less</span>
                      </button>
                      <span class="center-line"></span>
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
                    <%= userBean != null ? "Order Items" : "Login to Continue"%>
                </button>
            </a>
        </div>
    </div>
</div>
</body>
</html>
