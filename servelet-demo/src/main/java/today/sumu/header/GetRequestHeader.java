package today.sumu.header;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.URIParameter;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @Description
 * @Date Created in 22:20 2022/1/9
 * @Created by XZF
 **/

@WebServlet(urlPatterns = "/getRequestHeaderInfo")
public class GetRequestHeader extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("GetRequestHeader init");
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json;charset=utf-8");

        Enumeration<String> headerNames = req.getHeaderNames();
        HashMap<String, String> res = new HashMap<>();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            res.put(headerName, req.getHeader(headerName));
        }
        res.put("remoteAddr", req.getRemoteAddr());
        res.put("remotePort",""+ req.getRemotePort());
        res.put("remoteUser", req.getRemoteUser());
        res.put("remoteHost", req.getRemoteHost());
        PrintWriter out = resp.getWriter();
        out.println(JSON.toJSON(res).toString());
    }

    @Override
    public void destroy() {
        System.out.println("GetRequestHeader have been destroy");
        super.destroy();
    }
}
