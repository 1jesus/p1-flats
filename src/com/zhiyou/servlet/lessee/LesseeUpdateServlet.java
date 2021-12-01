package com.zhiyou.servlet.lessee;

import com.zhiyou.dao.ContractDao;
import com.zhiyou.dao.LesseeDao;
import com.zhiyou.model.contract.Contract;
import com.zhiyou.model.lessee.Lessee;

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
 * @Date 2021/9/14 15:46
 */
public class LesseeUpdateServlet extends HttpServlet {
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
        String lid = req.getParameter("lid");
        String lname = req.getParameter("lname");
        String ltel = req.getParameter("ltel");
        String lsex = req.getParameter("lsex");
        if ("男".equals(lsex)){
            lsex = String.valueOf(1);
        }else if ("女".equals(lsex)){
            lsex = String.valueOf(2);
        }
        String lnp = req.getParameter("lnp");
        String lidCard = req.getParameter("lidCard");
        String ladTime = req.getParameter("ladTime");
        System.out.println("打印接收到的参数 : lid = "+lid+"\r\n lname = "+lname+"\r\nltel = "+ltel+"\r\nlsex = "+lsex+"\r\nlnp = "+lnp+"\r\n lidCard = "+lidCard+"\r\nladTime = "+ladTime);

        //更新数据
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
        System.out.println("日志: LesseeUpdateServlet.doPost()  封装的对象  lessee= "+lessee);

        LesseeDao dao = new LesseeDao();
        dao.updateLesseeById(lessee);
        //返回展现全部
        req.getRequestDispatcher("/lessee/list").forward(req,resp);
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
        String lid = req.getParameter("lid");
        System.out.println("日志: LesseeUpdateServlet   doGet()  接收的参数lid = "+lid);

        //查询数据库
        LesseeDao dao = new LesseeDao();
        Lessee lessee = dao.findLesseeById(Integer.parseInt(lid));
        System.out.println("日志: LesseeUpdateServlet  doGet()  封装的对象 lessee = "+lessee);

        //把查询后的house信息,存入Session域
        HttpSession session = req.getSession();
        session.setAttribute("lessee",lessee);

        //跳转更新页面,回显数据
        req.getRequestDispatcher("/view/lessee/edit.jsp").forward(req,resp);
    }
}
