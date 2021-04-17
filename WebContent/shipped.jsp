<%--
  Created by IntelliJ IDEA.
  User: Karmit
  Date: 11-Apr.-2021
  Time: 11:32 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Shipped</title>
</head>
<body style="display: flex; width: 100%;height: 100%; justify-content:center;align-items: center;"
      onload="disableBackButton()">
<h1>Congratulations your order has been shipped!</h1>
<a href="dashboard.jsp"><h1>Browse more books</h1></a>
<script type="text/javaScript">
    function disableBackButton() {
        window.history.forward();
    }

    setTimeout("disableBackButton()", 0);
</script>
</body>
</html>
