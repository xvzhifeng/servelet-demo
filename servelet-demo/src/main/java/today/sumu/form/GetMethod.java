package today.sumu.form;


import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
