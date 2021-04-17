<%--
  Created by IntelliJ IDEA.
  User: Karmit
  Date: 11-Apr.-2021
  Time: 02:20 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>

<body style="display: flex; width: 100%;height: 100%; justify-content:center;align-items: center;"
      onload="disableBackButton()">
<h1>Uhh Oh! Your card information is invalid!</h1>
<br>
<a href="cart.jsp">
    <button>Retry</button>
</a>
<script type="text/javaScript">
    function disableBackButton() {
        window.history.forward();
    }

    setTimeout("disableBackButton()", 0);
</script>

</body>
</html>
