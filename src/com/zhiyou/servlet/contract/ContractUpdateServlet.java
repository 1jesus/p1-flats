package com.zhiyou.servlet.contract;

import com.zhiyou.dao.ContractDao;
import com.zhiyou.dao.UserDao;
import com.zhiyou.model.contract.Contract;
import com.zhiyou.model.house.House;

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
 * @Date 2021/9/13 19:47
 */
public class ContractUpdateServlet extends HttpServlet {
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
        //接收参数
        String cid = req.getParameter("cid");
        String cnum = req.getParameter("cnum");

        String chid = req.getParameter("chid");
        String clid = req.getParameter("clid");

        String cstartTime = req.getParameter("cstartTime");
        String cendTime = req.getParameter("cendTime");
        String ctotalMoney = req.getParameter("ctotalMoney");
        String cpayType = req.getParameter("cpayType");
        if ("月付".equals(cpayType)){
            cpayType = String.valueOf(1);
        }else if ("年付".equals(cpayType)){
            cpayType = String.valueOf(2);
        }
        //更新数据
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
        } catch (ParseException e) {
            e.printStackTrace();
        }
        contract.setCtotalMoney(ctotalMoney);
        contract.setCpayType(Integer.parseInt(cpayType));
        //日志
        System.out.println("cid = "+cid+"\r\n cnum = "+cnum+"\r\n chid = "+chid+"\r\n clid = "+clid+"\r\n cstartTime="+cstartTime+"\r\n cendTime = "+cendTime+"\r\n ctotalMoney = "+ctotalMoney+"\r\n cpayType = "+cpayType);

        ContractDao dao = new ContractDao();
        dao.updateContractById(contract);
        //返回展现全部
        req.getRequestDispatcher("/contract/list").forward(req,resp);
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
        String cid = req.getParameter("cid");
        System.out.println("日志: contractUpdateServlet   doGet()  接收的参数cid = "+cid);

        //查询数据库
        ContractDao dao = new ContractDao();
        Contract contract = dao.findContractById(Integer.parseInt(cid));
        System.out.println("日志: contractUpdateServlet  doGet()  封装的对象 contract = "+contract);

        //把查询后的contract信息,存入Session域
        HttpSession session = req.getSession();
        session.setAttribute("contract",contract);

        //跳转更新页面,回显数据
        req.getRequestDispatcher("/view/contract/edit.jsp").forward(req,resp);
    }
}
