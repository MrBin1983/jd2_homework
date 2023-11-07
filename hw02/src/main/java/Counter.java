import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet(name = "Counter", urlPatterns = "/count")
public class Counter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = "count.sav";
        File file = new File(path);
        int count = 0;

        if (file.isFile()) {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(file))){
                count = dis.readInt();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        count +=1;
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Counter</title></head>");
        out.println("<body><h1>Number of visits: " + count + "</h1>");
        out.println("</body></html>");

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(count);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }
}
