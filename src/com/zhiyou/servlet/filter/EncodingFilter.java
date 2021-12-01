package com.zhiyou.servlet.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Date 2021/9/17 14:28
 */
public class EncodingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //编码格式拦截
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //放行
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }


}
