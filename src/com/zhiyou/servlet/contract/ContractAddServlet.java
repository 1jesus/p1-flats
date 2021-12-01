package com.zhiyou.servlet.contract;

import com.zhiyou.dao.ContractDao;
import com.zhiyou.dao.UserDao;
import com.zhiyou.model.contract.Contract;
import com.zhiyou.model.house.House;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.attribute.AclFileAttributeView;
import java.security.cert.CRLReason;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Classname ${NAME}
 * @Date 2021/9/13 16:21
 */
public class ContractAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        //接收数据
        String cid = req.getParameter("cid");
        String cnum = req.getParameter("cnum");
        System.out.println("接收的 cnum = "+cnum);

        String chid = req.getParameter("chid");
        String clid = req.getParameter("clid");
        String cstartTime = req.getParameter("cstartTime");
        System.out.println("cstartTime = "+cstartTime);
        String cendTime = req.getParameter("cendTime");
        System.out.println("cendTime = "+cendTime);
        String ctotalMoney = req.getParameter("ctotalMoney");
        String cpayType = req.getParameter("cpayType");

        //封装数据
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Contract contract = new Contract();

        contract.setCid(Integer.parseInt(cid));
        contract.setCnum(cnum);
        contract.setChid(Integer.parseInt(chid));
        contract.setClid(Integer.parseInt(clid));
        try {
            //将字符串解析为日期
            contract.setCstartTime(sdf.parse(cstartTime));
            contract.setCendTime(sdf.parse(cendTime));

            //日志
            System.out.println("contract.setCstartTime(sdf.parse(cstartTime)) = "+sdf.parse(cstartTime));
            System.out.println("contract.setCendTime(sdf.parse(cendTime)) = "+sdf.parse(cendTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        contract.setCtotalMoney(ctotalMoney);
        contract.setCpayType(Integer.parseInt(cpayType));
        System.out.println("日志: HouseAddServlet.doPost()  封装的对象  contract= "+contract);

        //查询数据库
        ContractDao dao = new ContractDao();
        boolean isOK = dao.addContract(contract);
        System.out.println("查询返回值: isOK = "+isOK);

        //做出相应
        if (isOK){
            //添加成功,跳转到主页
            req.getRequestDispatcher("/contract/list").forward(req,resp);
        }else {
            //添加失败
            req.getRequestDispatcher("/view/contract/add.jsp").forward(req,resp);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
