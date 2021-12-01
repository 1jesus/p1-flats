package com.zhiyou.servlet.lessee;

import com.zhiyou.dao.ContractDao;
import com.zhiyou.dao.LesseeDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Date 2021/9/14 15:46
 */
public class LesseeDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //接收参数
        String lid = req.getParameter("lid");

        LesseeDao dao = new LesseeDao();
        dao.deleteById(Integer.parseInt(lid));

        req.getRequestDispatcher("/lessee/list").forward(req,resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
