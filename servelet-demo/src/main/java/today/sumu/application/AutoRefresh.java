package today.sumu.application;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Date Created in 22:42 2022/1/9
 * @Created by XZF
 **/

@WebServlet("/setResponse")
public class AutoRefresh extends HttpServlet {

    public SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    // 第一次调用时被执行
    @Override
    public void init() throws ServletException {
        System.out.println(simpleDateFormat.format(new Date().getTime()) + " SetResponse init");
        super.init();
    }

    // 后面的每次调用只执行service方法
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/Json;charset=utf-8");

        // Refresh 是一个响应头，告诉浏览器多久刷新一次
        resp.setIntHeader("Refresh", 5);
        resp.setStatus(201);
        Calendar calendar = Calendar.getInstance();
        Date time = calendar.getTime();
        Map<String, String> res = new HashMap<>();
        res.put("当前时间", simpleDateFormat.format(time));
        PrintWriter out = resp.getWriter();
        out.println(JSON.toJSON(res).toString());
    }

    // 关闭tomcat时执行
    @Override
    public void destroy() {
        System.out.println(simpleDateFormat.format(new Date().getTime()) + " SetResponse destroy");
        super.destroy();
    }
}
