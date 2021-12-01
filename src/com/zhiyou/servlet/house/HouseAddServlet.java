package com.zhiyou.servlet.house;

import com.zhiyou.dao.UserDao;
import com.zhiyou.model.house.House;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Date 2021/9/10 21:11
 */
public class HouseAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //接收数据
        String address = req.getParameter("haddress");
        String floor = req.getParameter("hfloor");
        String roomNum = req.getParameter("hroomNum");
        String area = req.getParameter("harea");
        String dir = req.getParameter("hdir");
        String deco = req.getParameter("hdeco");
        String air = req.getParameter("hair");
        String price = req.getParameter("hprice");
        String rentStatus = req.getParameter("hrentStatus");

        //封装数据
        House house = new House();
        house.setHaddress(address);
        house.setHfloor(floor);
        house.setHroomNum(Integer.parseInt(roomNum));
        house.setHarea(area);
        house.setHdir(dir);
        house.setHdeco(Integer.parseInt(deco));
        house.setHair(Integer.parseInt(air));
        house.setHprice(Integer.parseInt(price));
        house.setHrentStatus(Integer.parseInt(rentStatus));
        System.out.println("日志: HouseAddServlet.doPost()  封装的对象house = "+house);

        //查询数据库
        UserDao userDao = new UserDao();
        boolean isOK = userDao.addHouse(house);
        System.out.println("查询返回值: isOK = "+isOK);

        //做出相应
        if (isOK){
            //添加成功,跳转到主页
            req.getRequestDispatcher("/house/list").forward(req,resp);
        }else {
            //添加失败
            req.getRequestDispatcher("/add.jsp").forward(req,resp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
