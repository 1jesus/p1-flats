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
 * @Date 2021/9/11 16:55
 */
public class HouseUpdateServlet extends HttpServlet {
    /**
     * 保存已经修改由 form表单 发过来的数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset:utf-8");
        System.out.println("============================================");
        //接收参数
        String hid = req.getParameter("hid");
        String haddress = req.getParameter("haddress");
        String hfloor = req.getParameter("hfloor");
        String hroomNum = req.getParameter("hroomNum");
        String harea = req.getParameter("harea");
        String hdir = req.getParameter("hdir");
        String hdeco = req.getParameter("hdeco");
        if ("精装".equals(hdeco)){
            hdeco = String.valueOf(1);
        }else if ("简装".equals(hdeco)){
            hdeco = String.valueOf(2);
        }
        String hair = req.getParameter("hair");
        //将接收到的String类型,换为对应字符
        if (hair.equals("双气")){
            hair = String.valueOf(1);
        }else if (hair.equals("单气")){
            hair = String.valueOf(2);
        }
        String hprice = req.getParameter("hprice");
        String hrentStatus = req.getParameter("hrentStatus");
        if (hrentStatus.equals("已出租")){
            hrentStatus = String.valueOf(1);
        }else if (hrentStatus.equals("待出租")){
            hrentStatus = String.valueOf(2);
        }
        System.out.println();
        //更新数据
        House house = new House();
        house.setHid(Integer.parseInt(hid));
        house.setHaddress(haddress);
        house.setHfloor(hfloor);
        house.setHroomNum(Integer.parseInt(hroomNum));
        house.setHarea(harea);
        house.setHdir(hdir);
        house.setHdeco(Integer.parseInt(hdeco));
        house.setHair(Integer.parseInt(hair));
        house.setHprice(Double.parseDouble(hprice));
        house.setHrentStatus(Integer.parseInt(hrentStatus));
        System.out.println("id = "+hid+"\r\n address = "+haddress+"\r\nfloor = "+hfloor+"\r\nroomNum = "+hroomNum+"\r\nare"+harea+"\r\ndir="+hdir+"\r\nhdeco = "+hdeco+"\r\nair = "+hair+"\r\nprice = "+hprice+"\r\nrentStatus = "+hrentStatus);

        UserDao userDao = new UserDao();
        userDao.updateHouseById(house);
        //返回展现全部
        req.getRequestDispatcher("/house/list").forward(req,resp);
    }

    /**
     * 第一步: 更新数据前,先查询要更新哪一条数据,并把查询结果展现
     * 第二步: 在展现的数据上修改,点击提交按钮后保存
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //防止乱码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset:utf-8");

        //接收参数
        String id = req.getParameter("hid");
        System.out.println("日志: HouseUpdateServlet doGet()  接收的参数id = "+id);

        //查询数据库
        UserDao userDao = new UserDao();
        House house = userDao.findHouseById(Integer.parseInt(id));
        System.out.println("日志: HouseUpdateServlet  doGet()  封装的对象 house = "+house);

        //把查询后的house信息,存入Session域
        HttpSession session = req.getSession();
        session.setAttribute("house",house);

        //跳转更新页面,回显数据
        req.getRequestDispatcher("/view/flats/edit.jsp").forward(req,resp);
    }
}
