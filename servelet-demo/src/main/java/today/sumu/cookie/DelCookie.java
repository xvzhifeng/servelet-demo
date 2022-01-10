package today.sumu.cookie;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author sumu
 * @date 1/10/2022 8:25 PM
 */
@WebServlet(urlPatterns = "/delCookie")
public class DelCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        HashMap<String,String> res = new HashMap<>();
        for(Cookie cookie:cookies) {
            if("name".equals(cookie.getName())) {
                // delete cookie
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
                res.put("action", " delete cookie " + cookie.getValue());
            }
        }
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println(JSON.toJSON(res).toString());
    }
}
