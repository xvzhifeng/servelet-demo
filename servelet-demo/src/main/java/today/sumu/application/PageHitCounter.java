package today.sumu.application;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author sumu
 * @date 1/11/2022 4:25 PM
 */
@WebServlet("/pageHitCounter")
public class PageHitCounter extends HttpServlet {
    long count;
    @Override
    public void init() throws ServletException {
        count = 0;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        // 增加 hitCount
        count++;
        PrintWriter out = resp.getWriter();
        String title = "总点击量";
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<h2 align=\"center\">" + count + "</h2>\n" +
                "</body></html>");
    }
}
