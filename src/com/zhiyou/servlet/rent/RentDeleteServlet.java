package com.zhiyou.servlet.rent;

import com.zhiyou.dao.LesseeDao;
import com.zhiyou.dao.RentDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Date 2021/9/14 20:50
 */
public class RentDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //接收参数
        String rid = req.getParameter("rid");

        RentDao dao = new RentDao();
        dao.deleteById(Integer.parseInt(rid));

        req.getRequestDispatcher("/rent/list").forward(req,resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
