package today.sumu.form;


import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * @Description
 * @Date Created in 20:42 2022/1/8
 * @Created by XZF
 **/
@WebServlet(urlPatterns = "/getMethod")
public class GetMethod extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应内容类型 返回类型为json
        resp.setContentType("text/json;charset=UTF-8");

        // 输出流 输出到response
        PrintWriter out = resp.getWriter();
        String title = "使用 GET 方法读取表单数据";
        // 从request 中直接获取到前端传过来的数据；
        String name =new String(req.getParameter("name").getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
        String password =new String(req.getParameter("password").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);

        // 为名字和姓氏创建 Cookie
        Cookie name1 = new Cookie("name", name); // 中文转码
        Cookie url = new Cookie("url", req.getParameter("url"));

        // 为两个 Cookie 设置过期日期为 24 小时后
        name1.setMaxAge(60*60*24);
        url.setMaxAge(60*60);

        // 在响应头中添加两个 Cookie
        resp.addCookie(name1);
        resp.addCookie(url);

        // 使用fastJson 返回json信息
        HashMap<String,String> res = new HashMap<>();
        res.put("name",name);
        res.put("password",password);
        String toJSON = JSON.toJSON(res).toString();
        out.println(toJSON);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
