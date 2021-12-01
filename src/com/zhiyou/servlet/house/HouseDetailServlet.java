package com.zhiyou.servlet.house;

import com.zhiyou.dao.UserDao;
import com.zhiyou.model.house.House;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Date 2021/9/11 15:31
 */
public class HouseDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset:utf-8");

        //接收参数
        String id = req.getParameter("id");
        System.out.println("日志: HouseDetailServlet doPost() 接收的参数id = "+id);

        //查询数据库
        UserDao userDao = new UserDao();
        House house = userDao.detailById(Integer.parseInt(id));
        System.out.println("日志: HouseDetailServlet  detailById 封装的对象 house = "+house);

        //把查询后的house信息,存入Session域
        HttpSession session = req.getSession();
        session.setAttribute("house",house);

        req.getRequestDispatcher("/view/flats/detail.jsp").forward(req,resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
