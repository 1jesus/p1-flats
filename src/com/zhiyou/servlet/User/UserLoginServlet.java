package com.zhiyou.servlet.User;

import com.zhiyou.dao.UserDao;
import com.zhiyou.model.user.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Date 2021/9/9 17:18
 */
public class UserLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html:charset:utf-8");

        //接收参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println("日志: UserLoginServlet  username = "+username+" password = "+password);

        //查询数据库
        UserDao userDao = new UserDao();
        User user = userDao.findUserByUsernameAndPassword(username, password);
        System.out.println("日志: userDao.findUserByUsernameAndPassword  user = "+user);

        //如果user存在做出响应
        if (user != null){
            //登录成功
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("/admin.jsp").forward(req,resp);
        }else{
            //登录失败给出提示
            req.setAttribute("loginErrMsg","用户名或密码错误");
            req.getRequestDispatcher("/index.jsp").forward(req,resp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
