<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Product" %>

<html>
<head>
    <title>Danh sách sản phẩm</title>
</head>
<body>

<h2>Danh sách sản phẩm</h2>

<a href="/lab3_crud_simulation/product/create">
    Thêm sản phẩm
</a>

<table border="1" cellpadding="10">
    <tr>
        <th>ID</th>
        <th>Tên</th>
        <th>Giá</th>
        <th>Action</th>
    </tr>

    <%
        ArrayList<Product> list =
            (ArrayList<Product>) request.getAttribute("products");

        for(Product p : list){
    %>

    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getName() %></td>
        <td><%= p.getPrice() %></td>

        <td>
            <a href="/lab3_crud_simulation/product/edit/<%= p.getId() %>">
                Edit
            </a>

            <a href="/lab3_crud_simulation/product/delete/<%= p.getId() %>">
                Delete
            </a>
        </td>
    </tr>

    <% } %>

</table>

</body>
</html>