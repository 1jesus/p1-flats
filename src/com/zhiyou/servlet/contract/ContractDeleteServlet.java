package com.zhiyou.servlet.contract;

import com.zhiyou.dao.ContractDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname ${NAME}
 * @Date 2021/9/13 19:08
 */
public class ContractDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //接收参数
        String cid = req.getParameter("cid");

        ContractDao dao = new ContractDao();
        dao.deleteById(Integer.parseInt(cid));

        req.getRequestDispatcher("/contract/list").forward(req,resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
