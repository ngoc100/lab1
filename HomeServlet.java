package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
        "/home/index",
        "/home/about",
        "/home/contact",
        "/HomeServlet"
})
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String uri = request.getRequestURI();

        out.println("<html><head><meta charset=\"UTF-8\"><title>Home Servlet</title></head><body>");

        if (uri.contains("/home/index")) {
            out.println("<h1>Welcome to Home Page</h1>");
            out.println("<p><a href=\"" + request.getContextPath() + "/HomeServlet\">Contact Form</a></p>");
        }
        else if (uri.contains("/home/about")) {
            out.println("<h1>About Us Page</h1>");
            out.println("<p>This is the about page.</p>");
        }
        else if (uri.contains("/home/contact") || uri.contains("/HomeServlet")) {
            out.println("<h1>Contact Form</h1>");
            out.println("<form method=\"post\" action=\"" + request.getContextPath() + "/HomeServlet\">");
            out.println("<label for=\"name\">Name:</label><br>");
            out.println("<input type=\"text\" id=\"name\" name=\"name\" required><br><br>");
            out.println("<label for=\"email\">Email:</label><br>");
            out.println("<input type=\"email\" id=\"email\" name=\"email\" required><br><br>");
            out.println("<label>Choose topics you are interested in:</label><br>");
            out.println("<input type=\"checkbox\" id=\"opt1\" name=\"topics\" value=\"Java\"> <label for=\"opt1\">Java</label><br>");
            out.println("<input type=\"checkbox\" id=\"opt2\" name=\"topics\" value=\"Servlet\"> <label for=\"opt2\">Servlet</label><br>");
            out.println("<input type=\"checkbox\" id=\"opt3\" name=\"topics\" value=\"HTML\"> <label for=\"opt3\">HTML</label><br><br>");
            out.println("<label for=\"message\">Message:</label><br>");
            out.println("<textarea id=\"message\" name=\"message\" rows=\"5\" cols=\"40\" required></textarea><br><br>");
            out.println("<button type=\"submit\">Send</button>");
            out.println("</form>");
        } else {
            out.println("<h1>Page not found</h1>");
        }

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String message = request.getParameter("message");
        String[] topics = request.getParameterValues("topics");

        out.println("<html><head><meta charset=\"UTF-8\"><title>Contact Submitted</title></head><body>");
        out.println("<h1>Form Submitted</h1>");
        out.println("<p><strong>Name:</strong> " + (name == null ? "(none)" : name) + "</p>");
        out.println("<p><strong>Email:</strong> " + (email == null ? "(none)" : email) + "</p>");
        out.println("<p><strong>Selected topics:</strong> ");
        if (topics == null || topics.length == 0) {
            out.println("(none)");
        } else {
            out.println(String.join(", ", topics));
        }
        out.println("</p>");
        out.println("<p><strong>Message:</strong> " + (message == null ? "(none)" : message) + "</p>");
        out.println("<p><a href=\"" + request.getContextPath() + "/HomeServlet\">Back to form</a></p>");
        out.println("</body></html>");
    }
}
