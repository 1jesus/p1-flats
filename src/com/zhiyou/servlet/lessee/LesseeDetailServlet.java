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

/**
 * @Classname ${NAME}
 * @Date 2021/9/14 15:46
 */
public class LesseeDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //参数
        String lid = req.getParameter("lid");
        System.out.println("接收的lid = "+lid);
        //查询数据库
        LesseeDao dao = new LesseeDao();
         Lessee lessee = dao.detailById(Integer.parseInt(lid));

        req.getSession().setAttribute("lessee",lessee);

        req.getRequestDispatcher("/view/lessee/detail.jsp").forward(req,resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
