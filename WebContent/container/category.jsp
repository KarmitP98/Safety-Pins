<%--
  Created by IntelliJ IDEA.
  User: Karmit
  Date: 17-Apr.-2021
  Time: 04:15 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/common.scss">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/category.scss">
    <title>Category Container</title>
</head>
<body>

<div class="category">
    <div class="title">Category</div>
    <form method="post" action="#" style="width:50%;">
        <select id="category"
                name="category">
            <option value="all">All</option>
            <option value="Fiction">Fiction</option>
            <option value="Science">Science</option>
            <option value="Engineering">Engineering</option>
            <option value="Maths">Maths</option>
            <option value="Modern">Modern</option>
            <option value="Kids">Kids</option>
        </select>
        <br>
        <br>
        <button class='button button-primary' type="submit">Filter</button>
    </form>
</div>

</body>
</html>
