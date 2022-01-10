package today.sumu.exception;

import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.parser.JSONParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @author sumu
 * @date 1/10/2022 7:38 PM
 */
public class ErrorHandler extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("ErrorHandler");
        super.init();
    }

    // 处理 GET 方法请求的方法
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("ErrorHandler doGet");
        // 设置响应内容类型
        response.setContentType("text/json;charset=UTF-8");
        // 该属性给出异常产生的信息，信息可被存储，并在存储为 java.lang.Throwable 数据类型后可被分析。
        Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
        // 该属性给出状态码，状态码可被存储，并在存储为 java.lang.Integer 数据类型后可被分析。
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        // 该属性给出 Servlet 的名称，名称可被存储，并在存储为 java.lang.String 数据类型后可被分析。
        String servletName = (String) request.getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        // 该属性给出有关 URL 调用 Servlet 的信息，信息可被存储，并在存储为 java.lang.String 数据类型后可被分析。
        String requestUri = (String)
                request.getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }
        String message = (String) request.getAttribute("javax.servlet.error.message");
        String type =  (String) request.getAttribute("javax.servlet.error.exception_type");
        HashMap<String, String> map = new HashMap<>();
        map.put("javax.servlet.error.exception", throwable+"");
        map.put("javax.servlet.error.status_code",statusCode+"");
        map.put("javax.servlet.error.servlet_name", servletName);
        map.put("javax.servlet.error.request_uri", requestUri);
        map.put("javax.servlet.error.message", message);
        map.put("javax.servlet.error.exception_type",type);
        PrintWriter out = response.getWriter();
        out.println(JSON.toJSON(map).toString());
    }

    // 处理 POST 方法请求的方法
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
