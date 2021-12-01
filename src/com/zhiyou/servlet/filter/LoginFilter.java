package com.zhiyou.servlet.filter;

import com.zhiyou.model.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Date 2021/9/17 14:31
 */
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        User user = (User) session.getAttribute("user");
        System.out.println("日志: LoginFilter 拦截器----user.getUsername() = "+user.getUname());
        if (user.getUname() != null) {
            //登录,放行
            chain.doFilter(request, response);
        } else {
            //没有登陆,重定向到首页
            HttpServletResponse resp = (HttpServletResponse) response;
            resp.sendRedirect("/index.jsp");
        }
    }

    @Override
    public void destroy() {

    }

}
