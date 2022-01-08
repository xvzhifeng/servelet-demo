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
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @Description
 * @Date Created in 20:42 2022/1/8
 * @Created by XZF
 **/
@WebServlet(urlPatterns = "/postMethod")
public class PostMethod extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置响应内容类型 返回类型为json
        super.doGet(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=UTF-8");

        // 输出流 输出到response
        PrintWriter out = resp.getWriter();
        String title = "使用 POST 方法读取表单数据";
        HashMap<String,String> res = new HashMap<>();
        // 不确定前端传过来的数据的key时可以使用一下方法，可以获取所有的参数key
        Enumeration<String> parameterNames = req.getParameterNames();
        // 一旦我们有一个枚举，我们可以以标准方式循环枚举，使用 hasMoreElements() 方法来确定何时停止，使用 nextElement() 方法来获取每个参数的名称。
        while(parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String values = new String(req.getParameter(name).getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
            res.put(name,values);
        }
        String toJSON = JSON.toJSON(res).toString();
        out.println(toJSON);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
