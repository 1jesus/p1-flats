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
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Classname ${NAME}
 * @Date 2021/9/14 20:50
 */
public class RentAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

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

        //查询数据库
        RentDao dao = new RentDao();
        boolean isOK = dao.addRent(rent);
        System.out.println("查询返回值: isOK = "+isOK);

        //做出相应
        if (isOK){
            //添加成功,跳转到主页
            req.getRequestDispatcher("/rent/list").forward(req,resp);
        }else {
            //添加失败
            req.getRequestDispatcher("/view/rent/add.jsp").forward(req,resp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
