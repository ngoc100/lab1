package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
        "/product/index",
        "/product/create",
        "/product/edit/*",
        "/product/delete/*",
        "/ProductServlet"
})
public class ProductServlet extends HttpServlet {

    ArrayList<Product> list = new ArrayList<>();

    @Override
    public void init() {
        list.add(new Product(1, "Laptop", 1500));
        list.add(new Product(2, "Mouse", 20));
        list.add(new Product(3, "Keyboard", 50));
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String uri = request.getRequestURI();

        if (uri.contains("/index") || uri.contains("/ProductServlet")) {

            out.println("<html><head><meta charset=\"UTF-8\"><title>Product List</title></head><body>");
            out.println("<h1>Product List</h1>");
            out.println("<table border=\"1\" cellpadding=\"10\">");
            out.println("<tr><th>ID</th><th>Name</th><th>Price</th><th>Actions</th></tr>");
            
            for (Product p : list) {
                out.println("<tr>");
                out.println("<td>" + p.getId() + "</td>");
                out.println("<td>" + p.getName() + "</td>");
                out.println("<td>$" + String.format("%.2f", p.getPrice()) + "</td>");
                out.println("<td>");
                out.println("<a href=\"" + request.getContextPath() + "/product/edit/" + p.getId() + "\">Edit</a> | ");
                out.println("<a href=\"" + request.getContextPath() + "/product/delete/" + p.getId() + "\">Delete</a>");
                out.println("</td>");
                out.println("</tr>");
            }
            
            out.println("</table><br>");
            out.println("<a href=\"" + request.getContextPath() + "/product/create\">Add New Product</a>");
            out.println("</body></html>");

        } else if (uri.contains("/create")) {

            out.println("<html><head><meta charset=\"UTF-8\"><title>Create Product</title></head><body>");
            out.println("<h1>Create Product</h1>");
            out.println("<form method=\"post\" action=\"" + request.getContextPath() + "/product/create\">");
            out.println("<label for=\"id\">Product ID:</label><br>");
            out.println("<input type=\"number\" id=\"id\" name=\"id\" required><br><br>");
            out.println("<label for=\"name\">Product Name:</label><br>");
            out.println("<input type=\"text\" id=\"name\" name=\"name\" required><br><br>");
            out.println("<label for=\"price\">Price:</label><br>");
            out.println("<input type=\"number\" id=\"price\" name=\"price\" step=\"0.01\" required><br><br>");
            out.println("<button type=\"submit\">Create</button>");
            out.println("</form><br>");
            out.println("<a href=\"" + request.getContextPath() + "/ProductServlet\">Back to list</a>");
            out.println("</body></html>");

        } else if (uri.contains("/edit")) {

            String path = request.getPathInfo();
            int id = Integer.parseInt(path.substring(1));

            Product product = null;

            for (Product p : list) {
                if (p.getId() == id) {
                    product = p;
                    break;
                }
            }

            if (product != null) {
                out.println("<html><head><meta charset=\"UTF-8\"><title>Edit Product</title></head><body>");
                out.println("<h1>Edit Product</h1>");
                out.println("<form method=\"post\" action=\"" + request.getContextPath() + "/product/edit/" + id + "\">");
                out.println("<label for=\"id\">Product ID:</label><br>");
                out.println("<input type=\"number\" id=\"id\" name=\"id\" value=\"" + product.getId() + "\" disabled><br><br>");
                out.println("<label for=\"name\">Product Name:</label><br>");
                out.println("<input type=\"text\" id=\"name\" name=\"name\" value=\"" + product.getName() + "\" required><br><br>");
                out.println("<label for=\"price\">Price:</label><br>");
                out.println("<input type=\"number\" id=\"price\" name=\"price\" step=\"0.01\" value=\"" + product.getPrice() + "\" required><br><br>");
                out.println("<button type=\"submit\">Update</button>");
                out.println("</form><br>");
                out.println("<a href=\"" + request.getContextPath() + "/ProductServlet\">Back to list</a>");
                out.println("</body></html>");
            } else {
                out.println("<html><body><h1>Product not found</h1></body></html>");
            }

        } else if (uri.contains("/delete")) {

            String path = request.getPathInfo();
            int id = Integer.parseInt(path.substring(1));

            list.removeIf(p -> p.getId() == id);

            response.sendRedirect(request.getContextPath() + "/ProductServlet");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();

        if (uri.contains("/create")) {

            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            double price = Double.parseDouble(
                    request.getParameter("price"));

            Product product = new Product(id, name, price);

            list.add(product);

            response.sendRedirect(request.getContextPath() + "/ProductServlet");
        } else if (uri.contains("/edit")) {
            
            String path = request.getPathInfo();
            int id = Integer.parseInt(path.substring(1));
            String name = request.getParameter("name");
            double price = Double.parseDouble(request.getParameter("price"));
            
            for (Product p : list) {
                if (p.getId() == id) {
                    p.setName(name);
                    p.setPrice(price);
                    break;
                }
            }
            
            response.sendRedirect(request.getContextPath() + "/ProductServlet");
        }
    }
}