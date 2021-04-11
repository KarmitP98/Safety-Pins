<%--
  Created by IntelliJ IDEA.
  User: Karmit
  Date: 11-Apr.-2021
  Time: 02:49 p.m.
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
    <title>Product Detail</title>
</head>
<body>

<script>
    function addItemToCart(bid) {
        $.ajax({
            type: 'get',
            url: 'add-item',
            data: {bid: bid}
        })
    }
</script>
</body>
</html>
