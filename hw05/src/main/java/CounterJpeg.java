import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import static java.lang.System.out;

@WebServlet(name = "CounterJpeg", urlPatterns = "/cj")
public class CounterJpeg extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String userHomeDir = System.getProperty("user.home");
        String counterDirPath = userHomeDir + File.separator + "java" + File.separator + "hw05";
        new File(counterDirPath).mkdirs();
        String counterFilePath = counterDirPath + File.separator + "countJpeg.sav";
        File file = new File(counterFilePath);

        int count = 0;

        if (file.isFile()) {
            try (DataInputStream dis = new DataInputStream(new FileInputStream(file))) {
                count = dis.readInt();
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        }

        count += 1;

        BufferedImage countJpeg = new BufferedImage(500, 200, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = countJpeg.createGraphics();
        graphics.setFont(new Font("Serif", Font.ITALIC, 72));
        graphics.setColor(Color.BLUE);
        graphics.drawString(String.valueOf(count), 100, 100);

        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write(countJpeg, "jpeg", sos);

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(file))) {
            dos.writeInt(count);
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }
}
