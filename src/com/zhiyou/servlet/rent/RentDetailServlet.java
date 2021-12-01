package com.zhiyou.servlet.rent;

import com.zhiyou.dao.LesseeDao;
import com.zhiyou.dao.RentDao;
import com.zhiyou.model.lessee.Lessee;
import com.zhiyou.model.rent.Rent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Date 2021/9/14 20:50
 */
public class RentDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //参数
        String rid = req.getParameter("rid");
        System.out.println("接收的rid = "+rid);
        //查询数据库
        RentDao dao = new RentDao();
        Rent rent = dao.detailById(Integer.parseInt(rid));

        req.getSession().setAttribute("rent",rent);

        req.getRequestDispatcher("/view/rent/detail.jsp").forward(req,resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
