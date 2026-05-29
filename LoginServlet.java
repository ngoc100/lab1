package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {
        "/LoginServlet",
        "/login/form",
        "/login/check"
})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<html><body>");

        out.println("<h2>Login Form</h2>");

        out.println("<form method='POST' action='check'>");

        out.println("Username: <input type='text' name='username'><br><br>");

        out.println("Password: <input type='password' name='password'><br><br>");

        out.println("Gender:");
        out.println("<input type='radio' name='gender' value='Male'> Male");
        out.println("<input type='radio' name='gender' value='Female'> Female");
        out.println("<br><br>");

        out.println("Hobbies:");
        out.println("<input type='checkbox' name='hobbies' value='Music'> Music");
        out.println("<input type='checkbox' name='hobbies' value='Game'> Game");
        out.println("<input type='checkbox' name='hobbies' value='Sport'> Sport");
        out.println("<br><br>");

        out.println("City:");
        out.println("<select name='city'>");
        out.println("<option value='Da Nang'>Da Nang</option>");
        out.println("<option value='Ha Noi'>Ha Noi</option>");
        out.println("<option value='HCM'>HCM</option>");
        out.println("</select><br><br>");

        out.println("<button type='submit'>Submit</button>");

        out.println("</form>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");

        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String city = request.getParameter("city");

        String[] hobbies = request.getParameterValues("hobbies");

        out.println("<html><body>");
        out.println("<h2>Thông tin đăng nhập</h2>");

        if (password == null || !password.equals("123")) {
            out.println("<h3 style='color:red'>Sai mật khẩu!</h3>");
        }

        out.println("<table border='1' cellpadding='10'>");

        out.println("<tr>");
        out.println("<td>Username</td>");
        out.println("<td>" + username + "</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Password</td>");
        out.println("<td>" + password + "</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Gender</td>");
        out.println("<td>" + gender + "</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>City</td>");
        out.println("<td>" + city + "</td>");
        out.println("</tr>");

        out.println("<tr>");
        out.println("<td>Hobbies</td>");
        out.println("<td>");

        if (hobbies != null) {
            for (String hobby : hobbies) {
                out.println(hobby + " ");
            }
        } else {
            out.println("Không chọn");
        }

        out.println("</td>");
        out.println("</tr>");

        out.println("</table>");

        out.println("</body></html>");
    }
}
