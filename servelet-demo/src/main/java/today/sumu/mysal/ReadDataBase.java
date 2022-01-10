package today.sumu.mysal;


import com.alibaba.fastjson.JSON;

import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author sumu
 * @date 1/10/2022 9:14 PM
 */
@WebServlet("/readDataBase")
public class ReadDataBase extends HttpServlet {

    private static final long serialVersionUID = 1L;
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3307/movies?useUnicode=true&characterEncoding=utf-8&serverTimezone=GMT%2B8&useSSL=false";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReadDataBase() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;
        resp.setContentType("text/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        try {
            // 注册 JDBC 驱动器
            Class.forName(JDBC_DRIVER);
            // 打开一个连接
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            // 执行 SQL 查询
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT userID, username, password FROM user";
            ResultSet rs = stmt.executeQuery(sql);
            HashMap<String,HashMap<String,String>> map = new HashMap<>();
            while(rs.next()) {
                HashMap<String,String> res = new HashMap<>();
                res.put("userID",rs.getString("userID"));
                res.put("username",rs.getString("username"));
                res.put("password",rs.getString("password"));
                map.put(rs.getString("userID"),res);
            }
            String s = JSON.toJSON(map).toString();
            out.println(s);
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 最后是用于关闭资源的块
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
