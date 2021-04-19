<%@ page import="ctrl.ShoppingCart" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="bean.BookBean" %>
<%@ page import="bean.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/cart-item-view.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.scss">
    <title>Cart Item View</title>
</head>
<body>
<% HashMap<BookBean, Integer> cart = (HashMap<BookBean, Integer>) session.getAttribute("cart");
    out.print(cart);
    UserBean user = (UserBean) session.getAttribute("user");

    String title = request.getParameter("title");
%>

<div class="main-content-area">
    <div class="order-details" id="order-details" style="width: 50%;">
        <div class="title"><%=title%>></div>
<%--        <a class="remove-link">Remove all items</a>--%>
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
                        <img alt="Book Cover 6" src="/assets/book-cover-16.jpg"/>
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
            <a href='<%= user!= null ? "reviewOrder.jsp":"login.html"%>'>
                <button class="button button-primary">
                    <%= user != null ? "Proceed to Checkout" : "Login to Continue"%>
                </button>
            </a>

        </div>
    </div>
</div>
</body>
</html>
