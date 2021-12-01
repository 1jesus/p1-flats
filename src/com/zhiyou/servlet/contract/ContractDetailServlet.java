package com.zhiyou.servlet.contract;

import com.zhiyou.dao.ContractDao;
import com.zhiyou.model.contract.Contract;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @Classname ${NAME}
 * @Date 2021/9/13 19:22
 */
public class ContractDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //参数
        String cid = req.getParameter("cid");
        System.out.println("接收的cid = "+cid);
        //查询数据库
        ContractDao dao = new ContractDao();
        Contract contract = dao.detailById(Integer.parseInt(cid));

        req.getSession().setAttribute("contract",contract);

        req.getRequestDispatcher("/view/contract/detail.jsp").forward(req,resp);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
