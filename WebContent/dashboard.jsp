<%@ page import="bean.UserBean" %><%--
  Created by IntelliJ IDEA.
  User: Karmit
  Date: 11-Apr.-2021
  Time: 11:16 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.scss">
    <title>Dashboard</title>
</head>
<body>

<jsp:include page="/container/navbar.jsp">
    <jsp:param name="from" value="Home"/>
</jsp:include>

<div class="article">
    <jsp:include page="/container/category.jsp"/>
    <jsp:include page="/container/catalogue.jsp">
        <jsp:param name="search" value='<%=request.getParameter("search")%>'/>
        <jsp:param name="category" value='<%=request.getParameter("category")%>'/>
    </jsp:include>
</div>


<style>
    .article {
        display: flex;
        position: relative;
        width: 100%;
        min-height: 90%;
        height: fit-content;
        background-color: var(--background);
    }
</style>
</body>
</html>
