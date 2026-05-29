<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="model.Product" %>

<%
    Product p = (Product) request.getAttribute("product");
%>

<html>
<head>
    <title>Edit Product</title>
</head>
<body>

<h2>Thông tin sản phẩm</h2>

<p>ID: <%= p.getId() %></p>

<p>Name: <%= p.getName() %></p>

<p>Price: <%= p.getPrice() %></p>

</body>
</html>