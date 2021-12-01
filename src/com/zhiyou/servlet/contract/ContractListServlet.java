package com.zhiyou.servlet.contract;

import com.zhiyou.dao.ContractDao;
import com.zhiyou.dao.UserDao;
import com.zhiyou.model.contract.Contract;
import com.zhiyou.model.house.House;
import com.zhiyou.model.lessee.Lessee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ${NAME}
 * @Date 2021/9/13 14:56
 */
public class ContractListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset:utf-8");

        ContractDao dao = new ContractDao();
        //=========================== 模糊查询 ===========================
        String field = req.getParameter("field");
        if (field == null || "".equals(field)){
            field = null;
        }
        System.out.println("field = "+field);
        /**
         * 登陆成功后就已经跳转到 HouseListServlet 类查询并展现全部了
         * 但是此时并没有点击搜索框,也就是说此时 keyword 没有值
         * ==============  和当前页(pageNo)一样都要判断是否为空 ==============
         *
         * 所以判断一下 keyword 是否为空字符串
         */
        String keyword = req.getParameter("keyword");
        if (keyword == null || "".equals(keyword)){
            keyword = null;
        }
        System.out.println("keyword = "+keyword);
        //=========================== 模糊查询 ===========================

        //=========================== 分页 ===========================
        //每页展现多少条数据
        String pageNoStr = req.getParameter("pageNo");
        int pageNo = 0;
        if (pageNoStr == null || "".equals(pageNoStr)){
            //登录进去时,当前页并没有参数,给当前页设置默认值1
            pageNo = 1;
        }else {
            pageNo = Integer.parseInt(pageNoStr);
        }

        int pageSize = 3;
        //总共有多少条数据
        int total = dao.total(field,keyword);
        System.out.println("日志: ContractListServlet total = "+total);

        //算出总页数
        int pageCount = total%pageSize != 0 ? (total/pageSize)+1 : total/pageSize;
        int start = (pageNo-1)*pageSize;//当前页为第几页
        int end = pageSize;             //每页展示多少条数据
        //=========================== 分页 ===========================

        //查询并展现全部
        List<Contract> list = dao.findContractAll(start,end,field,keyword);
        System.out.println("日志 contractListServlet   doPost() list = "+list);

/*===========================================多表联查的字段信息======================================================*/
        //查询关联表house的hid和haddress字段的信息
        ArrayList<House> houseInfo = dao.getHouseInfo();
        req.getSession().setAttribute("houseInfo",houseInfo);
        System.out.println("======================houseInfo ="+houseInfo);

        //查询关联表lessee的lid和lname字段的信息
        ArrayList<Lessee> lesseeInfo = dao.getLesseeInfo();
        req.getSession().setAttribute("lesseeInfo",lesseeInfo);
        System.out.println("======================lesseeInfo ="+lesseeInfo);
/*==================================================================================================================*/


        HttpSession session = req.getSession();
        session.setAttribute("list",list);

/*        //多表联查后的结果存入域
        session.setAttribute("contract1",contract);*/

        //把分页数据存入session域
        session.setAttribute("pageNo",pageNo);
        session.setAttribute("pageCount",pageCount);
        session.setAttribute("total",total);
        session.setAttribute("field",field);
        session.setAttribute("keyword",keyword);

        req.getRequestDispatcher("/view/contract/list.jsp").forward(req,resp);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
