package today.sumu.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * @author sumu
 * @date 1/10/2022 7:29 PM
 */

public class FilterConfig implements Filter {
    // 初始化 只会初始化一次
    @Override
    public void init(javax.servlet.FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig.getInitParameter("test"));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("FilterConfig doFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
