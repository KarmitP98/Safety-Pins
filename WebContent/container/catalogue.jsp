<%@ page import="bean.BookBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.MainModel" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: Karmit
  Date: 17-Apr.-2021
  Time: 04:44 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/catalogue.scss">
    <title>Catalogue</title>
</head>
<body>
<%
    String search = request.getParameter("search");
    String category = request.getParameter("category");
    ArrayList<BookBean> books;

    System.out.println("search = " + search);
    System.out.println("category = " + category);

    if (search.trim().length() > 0 && !search.equals("null")) {
        System.out.println("Search Book");
        books = MainModel.getInstance().searchBooks(search);
    } else if (category.trim().length() > 0 && !category.equals("all") && !category.equals("null")) {
        System.out.println("Search books by category");
        books = MainModel.getInstance().getBooksByCategory(category);
    } else {
        System.out.println("Fetch all books");
        books = MainModel.getInstance().getAllBooks();
    }
%>
<div class="content">
    <% for (BookBean book : books) {%>
    <jsp:include page="/container/item-card.jsp">
        <jsp:param name="bid" value='<%=book.getBid()%>'/>
        <jsp:param name="title" value='<%=book.getTitle()%>'/>
        <jsp:param name="author" value='<%=book.getAuthor()%>'/>
        <jsp:param name="price" value='<%=book.getPrice()%>'/>
        <jsp:param name="imageURL" value='<%=book.getPicture()%>'/>
    </jsp:include>
    <%}%>
</div>
</body>
</html>
