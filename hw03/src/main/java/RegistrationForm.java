import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "RegistrationForm", urlPatterns = "/regform")
public class RegistrationForm extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String tel = req.getParameter("telephone");
        String email = req.getParameter("email");
        resp.setContentType("text/html");

        if (name.length() == 0) {
            try (PrintWriter out = resp.getWriter()) {
                out.println("<html>");
                out.println("<h2>Invalid name</h2>");
                out.println("<a href='/hw03/form.html'>Back</a>");
                out.println("</html>");
            }
        }
        if (tel.length() == 0 && email.length() == 0) {
            try (PrintWriter out = resp.getWriter()) {
                out.println("<html>");
                out.println("<h2>Invalid Telephone or Email</h2>");
                out.println("<a href='/hw03/form.html'>Back</a>");
                out.println("</html>");
            }
        } else {

            try (PrintWriter out = resp.getWriter()) {
                out.println("<html>");
                out.println("<h2>Name: " + name + "</h2>");
                out.println("<h2>Telephone: " + tel + "</h2>");
                out.println("<h2>Email: " + email + "</h2>");
                out.println("<a href='/hw03/form.html'>Back</a>");
                out.println("</html>");
            }
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}