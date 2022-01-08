package today.sumu.helloWorld;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description
 * @Date Created in 16:47 2022/1/8
 * @Created by XZF
 **/
public class HelloWord extends HttpServlet {
    private String message;

    //生命周期 init -> service ->[doGet、doPost、doPut、doDelete ] -> destroy
    public void init() throws ServletException
    {
        // 执行必需的初始化
        message = "Hello World";
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        // 设置响应内容类型
        response.setContentType("text/html");

        // 实际的逻辑是在这里
        PrintWriter out = response.getWriter();
        out.println("<h1>" + message + "</h1>");
    }

    /**
     *  销毁当前的servlet
     */
    public void destroy()
    {
        // 什么也不做
    }

}
