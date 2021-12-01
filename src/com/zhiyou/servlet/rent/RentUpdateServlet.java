package com.zhiyou.servlet.rent;

import com.zhiyou.dao.LesseeDao;
import com.zhiyou.dao.RentDao;
import com.zhiyou.model.lessee.Lessee;
import com.zhiyou.model.rent.Rent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Classname ${NAME}
 * @Date 2021/9/14 20:50
 */
public class RentUpdateServlet extends HttpServlet {
    /**
     * 更新后提交数据的保存
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset:utf-8");

        System.out.println("============================================");
        //接收数据
        String rid = req.getParameter("rid");
        String rhid = req.getParameter("rhid");
        String rlid = req.getParameter("rlid");
        String rprice = req.getParameter("rprice");
        String rpayTime = req.getParameter("rpayTime");
        System.out.println("打印接收到的参数 : rid = "+rid+"\r\n rhid = "+rhid+"\r\nrlid = "+rlid+"\r\nrprice = "+rprice+"\r\nrpayTime = "+rpayTime);

        //封装数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Rent rent = new Rent();
        rent.setRid(Integer.parseInt(rid));
        rent.setRhid(Integer.parseInt(rhid));
        rent.setRlid(Integer.parseInt(rlid));
        rent.setRprice(Double.parseDouble(rprice));
        try {
            //将字符串解析为日期
            rent.setRpayTime(sdf.parse(rpayTime));
            //日志
            System.out.println("sdf.parse(rpayTime) = "+sdf.parse(rpayTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("日志: RentAddServlet.doPost()  封装的对象  rent = "+rent);

        RentDao dao = new RentDao();
        dao.updateRentById(rent);
        //返回展现全部
        req.getRequestDispatcher("/rent/list").forward(req,resp);
    }

    /**
     * 更新前的查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset:utf-8");

        //接收参数
        String rid = req.getParameter("rid");
        System.out.println("日志: RentUpdateServlet   doGet()  接收的参数rid = "+rid);

        //查询数据库
        RentDao dao = new RentDao();
        Rent rent = dao.findeRentById(Integer.parseInt(rid));
        System.out.println("日志: RentUpdateServlet  doGet()  封装的对象 rent = "+rent);

        //把查询后的house信息,存入Session域
        HttpSession session = req.getSession();
        session.setAttribute("rent",rent);

        //跳转更新页面,回显数据
        req.getRequestDispatcher("/view/rent/edit.jsp").forward(req,resp);
    }
}
