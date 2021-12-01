package com.zhiyou.servlet.filter;

import com.zhiyou.model.user.Role;
import com.zhiyou.model.user.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Classname ${NAME}
 * @Date 2021/9/16 22:22
 */
public class AuthFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        System.out.println("拦截器执行了");
        //先从请求中获得身份信息
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        String uri = req.getRequestURI();
        System.out.println("当前请求路径 : requestURI = "+uri);

        //取出角色数据
        ArrayList<Role> roleList = user.getRoleList();
        boolean isOK = false;
        for (Role role : roleList) {
            int id = role.getRoid();
            if ( (("/house/add".equals(uri) )&&(id == 1 || id ==2)) || (("/house/update".equals(uri) )&&(id == 1 || id ==2)) ){
                isOK = true;
            }else if ( (("/contract/update".equals(uri) )&&(id == 3 || id ==4)) || (("/contract/add".equals(uri) )&&(id == 3 || id ==4)) ){
                isOK = true;
            }else if ( (("/lessee/update".equals(uri) )&&(id == 6 || id == 5)) || (("/lessee/add".equals(uri) )&&(id == 6 || id ==4)) ){
                isOK = true;
            }else if ( (("/house/delete".equals(uri) )&&(id == 2)) || (("/contract/delete".equals(uri) )&&(id == 4)) || (("/lessee/delete".equals(uri) )&&(id == 6)) ){
                isOK = true;
            }
        }
        if (isOK) {
            System.out.println("拦截器已放行");
            chain.doFilter(request, response);
        }else{
            response.getWriter().write("<html><body>没有权限!!</body></html>");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
