import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "WhichBrowser", urlPatterns = "/wb")
public class WhichBrowser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userAgent = req.getHeader("User-Agent");

        PrintWriter out = resp.getWriter();
        out.println("<html><head><title>Which Browser</title>" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">" +
                "</head>");


        if (userAgent.contains("YaBrowser")) {
            out.println("<body><h1>Приветствую пользователя Yandex</h1>");
        } else if (userAgent.contains("Edg")) {
            out.println("<body><h1>Приветствую пользователя Edge</h1>");
        } else if (userAgent.contains("OPR")) {
            out.println("<body><h1>Приветствую пользователя Opera</h1>");
        } else if (userAgent.contains("Chrome")) {
            out.println("<body><h1>Приветствую пользователя Chrome</h1>");
        } else {
            out.println("<body><h1>Приветствую пользователя Firefox</h1>");
        }

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}



