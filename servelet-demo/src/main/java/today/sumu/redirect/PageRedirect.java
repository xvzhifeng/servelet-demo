package today.sumu.redirect;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sumu
 * @date 1/11/2022 4:17 PM
 */
@WebServlet("/redirectBaidu")
public class PageRedirect extends HttpServlet {
    @Override
    public void init() throws ServletException {
        System.out.println("PageRedirect init");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 该方法把响应连同状态码和新的网页位置发送回浏览器
//        resp.sendRedirect("http://www.baidu.com");

        // 也可以通过把 setStatus() 和 setHeader() 方法一起使用来达到同样的效果
        String site = "http://www.baidu.com" ;
        resp.setStatus(resp.SC_MOVED_TEMPORARILY);
        resp.setHeader("Location", site);
    }
}
