package com.zhiyou.servlet.lessee;

import com.zhiyou.dao.ContractDao;
import com.zhiyou.dao.LesseeDao;
import com.zhiyou.model.contract.Contract;
import com.zhiyou.model.lessee.Lessee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Classname ${NAME}
 * @Date 2021/9/14 15:46
 */
public class LesseeAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //接收数据
        String lid = req.getParameter("lid");
        String lname = req.getParameter("lname");
        String ltel = req.getParameter("ltel");
        String lsex = req.getParameter("lsex");
        String lnp = req.getParameter("lnp");
        String lidCard = req.getParameter("lidCard");
        String ladTime = req.getParameter("ladTime");
        System.out.println("打印接收到的参数 : lid = "+lid+"\r\n lname = "+lname+"\r\nltel = "+ltel+"\r\nlsex = "+lsex+"\r\nlnp = "+lnp+"\r\n lidCard = "+lidCard+"\r\nladTime = "+ladTime);

        //封装数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Lessee lessee = new Lessee();
        lessee.setLid(Integer.parseInt(lid));
        lessee.setLname(lname);
        lessee.setLtel(ltel);
        lessee.setLsex(Integer.parseInt(lsex));
        lessee.setLnp(lnp);
        lessee.setLidCard(lidCard);
        try {
            //将字符串解析为日期
            lessee.setLadTime(sdf.parse(ladTime));
            //日志
            System.out.println("sdf.parse(ladTime) = "+sdf.parse(ladTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("日志: LesseeAddServlet.doPost()  封装的对象  lessee= "+lessee);

        //查询数据库
        LesseeDao dao = new LesseeDao();
        boolean isOK = dao.addLessee(lessee);
        System.out.println("查询返回值: isOK = "+isOK);

        //做出相应
        if (isOK){
            //添加成功,跳转到主页
            req.getRequestDispatcher("/lessee/list").forward(req,resp);
        }else {
            //添加失败
            req.getRequestDispatcher("/view/lessee/add.jsp").forward(req,resp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
