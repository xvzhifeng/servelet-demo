package today.sumu.session;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author sumu
 * @date 1/10/2022 8:55 PM
 */
@WebServlet(urlPatterns = "/operationSession")
public class OperationSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        HashMap<String, String> map = new HashMap<>();
        // 获取 session 创建时间
        Date createTime = new Date(session.getCreationTime());
        // 获取该网页的最后一次访问时间
        Date lastAccessTime = new Date(session.getLastAccessedTime());
        //设置日期输出的格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("date", df.format(lastAccessTime));
        Integer per = (Integer) session.getAttribute("per");
        if(per == null) {
            session.setAttribute("per",0);
        } else {
            session.setAttribute("per",per+1);
        }
        // 删除session
        // session.invalidate();
        map.put("访问次数", String.valueOf(per));
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.println(JSON.toJSON(map));
    }
}
