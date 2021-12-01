package com.zhiyou.servlet.house;

import com.zhiyou.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Date 2021/9/10 23:12
 */
public class HouseDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset:utf-8");

        //接收参数
        String hid = req.getParameter("hid");
        System.out.println("日志: HouseDeleteServlet doPost()  接收的参数id = "+hid);

        //查询数据库
        UserDao userDao = new UserDao();
        userDao.deleteById(Integer.parseInt(hid));

        //执行完删除操作后,跳转页面展现全部
        req.getRequestDispatcher("/house/list").forward(req,resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
