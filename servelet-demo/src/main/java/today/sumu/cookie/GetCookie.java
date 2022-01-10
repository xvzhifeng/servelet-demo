package today.sumu.cookie;

import com.alibaba.fastjson.JSON;

import javax.jws.WebService;
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
@WebServlet(urlPatterns = "/getCookie")
public class GetCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        HashMap<Cookie,HashMap<String,String>> res = new HashMap<>();
        for(Cookie cookie:cookies) {
            HashMap<String,String> sub = new HashMap<>();
            sub.put("name", cookie.getName());
            sub.put("MaxAge",cookie.getMaxAge()+"");
            sub.put("Domin",cookie.getDomain());
            sub.put("path", cookie.getPath());
            sub.put("value",cookie.getValue());
            sub.put("comment",cookie.getComment());
            res.put(cookie,sub);
        }
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println(JSON.toJSON(res).toString());
    }
}
