<%--
  Created by IntelliJ IDEA.
  User: Karmit
  Date: 11-Apr.-2021
  Time: 11:26 a.m.
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
    <title>Add Payment Information</title>
</head>
<body>
<nav>
    <div class="navbar">
        <div id="logo">
            <img alt="Red Logo" src="assets/logo-red-bg.png"/>
        </div>
        <div class="options-area">
            <div class="cart-btn">
                <span class="material-icons">home </span>
            </div>
            <span class="center-line"></span>
            <div class="logout-btn">
                <span class="material-icons">logout</span>
            </div>
        </div>
    </div>
</nav>
<div class="main-content-area">
    <div class="add-info">
        <div class="input-area">
            <div class="title">Shipping Address</div>
            <fieldset id="ad_field">
                <label for="addressLine">Address</label>
                <input
                        id="addressLine"
                        name="addressLine"
                        placeholder="Enter your address"
                        type="text"
                />
            </fieldset>
            <div class="city">
                <fieldset id="city_field">
                    <label for="city">City</label>
                    <input
                            id="city"
                            name="city"
                            placeholder="Sand City"
                            type="text"
                    />
                </fieldset>
                <fieldset id="st_field">
                    <label for="state">State</label>
                    <input
                            id="state"
                            name="state"
                            placeholder="Arizona"
                            type="text"
                    />
                </fieldset>
                <fieldset id="con_field">
                    <label for="country">Country</label>
                    <input
                            id="country"
                            name="country"
                            placeholder="USA"
                            type="text"
                    />
                </fieldset>
            </div>
            <fieldset id="zip_field">
                <label for="zip">ZIP</label>
                <input id="zip" name="zip" placeholder="123 456" type="text"/>
            </fieldset>
            <fieldset id="phone_field">
                <label for="phone">Phone</label>
                <input
                        id="phone"
                        name="phone"
                        placeholder="+1-123-456-7890"
                        type="text"
                />
            </fieldset>
            <div class="title">Add a Payment Method</div>
            <fieldset id="card_field">
                <label for="card">Card Number</label>
                <input
                        id="card"
                        name="card"
                        placeholder="1212 3434 5656"
                        type="text"
                />
            </fieldset>
            <div class="card-detail">
                <fieldset id="cvs_field">
                    <label for="cvs">CVS</label>
                    <input id="cvs" name="cvs" placeholder="789" type="number"/>
                </fieldset>
                <fieldset id="exp_field">
                    <label for="expiry">Expiry Date</label>
                    <input
                            id="expiry"
                            name="expiry"
                            placeholder="12/12/2020"
                            type="date"
                    />
                </fieldset>
            </div>
        </div>
        <div class="order-total">
            <div class="card-title">
                <b>Subtotal</b> (3 Items): <b>$ 131.93</b>
            </div>
            <div class="card-subtitle">Taxes will be calculated at checkout</div>
            <br/><br/>
            <div class="card-body">
                <button class="button button-primary">Proceed to checkout</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
