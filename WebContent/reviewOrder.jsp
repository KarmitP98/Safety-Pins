<%@ page import="bean.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: Karmit
  Date: 11-Apr.-2021
  Time: 11:07 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="styles.css" rel="stylesheet"/>
    <link
            href="https://fonts.googleapis.com/icon?family=Material+Icons"
            rel="stylesheet"
    />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="cart.js"></script>
    <script type="javascript" src="order-review.js"></script>
    <title>Review Order</title>
</head>
<body>
<% UserBean userBean = (UserBean) session.getAttribute("user");
    if (userBean == null) {
        response.sendRedirect("login.html");
    }
%>
<nav>
    <div class="navbar">
        <a href="home.jsp">
            <div id="logo">
                <img alt="Red Logo" src="assets/logo-red-bg.png"/>
            </div>
        </a>
        <div class="options-area">
            <a href="home.jsp">
                <div class="cart-btn">
                    <span class="material-icons">home</span>
                </div>
            </a>
            <span class="center-line"></span>
            <a href="<%= userBean!= null ? "/logout":"login.html"%>">
                <div class="logout-btn">
                    <span class="material-icons"><%= userBean!= null ? "logout":"login"%></span>
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
                    <p class="address">123 Playground Street</p>
                    <p class="city">Bussiness Park, LA, 123 456</p>
                    <p class="country">USA</p>
                    <p class="phone">Phone: +1-215-512-2525</p>
                </div>
            </div>
            <div class="section">
                <div class="section-title">Choose a payment method</div>
                <div class="section-body">
                    <p class="card" id="card-info">
                        <span class="material-icons">payment</span>ending in 4848
                    </p>
                    <a href="add-info.jsp">Change payment method</a>
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

    <div class="order-detail-total">
        <div class="card-title">
            <b>Order Summary</b>
        </div>
        <div class="card-body">
            <div class="card-list">
                <div class="card-list-item">
              <span>4 Items</span
              ><span class="values" id="price">$ 131.93</span>
                </div>
                <div class="card-list-item">
              <span>Shipping</span
              ><span class="values" id="shipping">$ 0.00</span>
                </div>
                <div class="list-partition"></div>
                <div class="card-list-item">
              <span id="tax_text">Total before taxes</span
              ><span class="values" id="bTaxes">$ 131.93</span>
                </div>
                <div class="card-list-item">
              <span>Est. GST/HST</span
              ><span class="values" id="gst">$ 17.15</span>
                </div>
                <div class="card-list-item">
              <span>Est. PST/QST</span
              ><span class="values" id="pst">$ 0.00</span>
                </div>
                <div class="list-partition"></div>
                <div class="card-list-item" id="order-total">
              <span>Order Total</span
              ><span class="values" id="total">$ 149.08</span>
                </div>
            </div>
        </div>
        <button class="button button-primary" onclick="orderItems()">Check Out</button>
    </div>
</div>
</body>
</html>
