package com.zhiyou.servlet.house;

import com.zhiyou.dao.UserDao;
import com.zhiyou.model.house.House;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Classname ${NAME}
 * @Date 2021/9/9 17:19
 */
public class HouseListServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
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
        int total = userDao.total(field,keyword);
        System.out.println("日志: HouseListServlet total = "+total);

        //算出总页数
        int pageCount = total%pageSize != 0 ? (total/pageSize)+1 : total/pageSize;
        int start = (pageNo-1)*pageSize;//当前页为第几页
        int end = pageSize;             //每页展示多少条数据

        //=========================== 分页 ===========================
        //查询全部(查询的是房屋的所有信息)
        List<House> list =  userDao.findAll(start,end,field,keyword);

        //打印日志,判断是对象是否封装成功,是否查询成功
        System.out.println("日志:HouseListServlet.doPost() 查询全部list =  "+list );

        // Session域存入数据
        HttpSession session = req.getSession();
        session.setAttribute("list",list);
        //把分页数据存进去
        session.setAttribute("total",total);
        session.setAttribute("pageCount",pageCount);
        session.setAttribute("pageNo",pageNo);
        session.setAttribute("field",field);
        session.setAttribute("keyword",keyword);


        req.getRequestDispatcher("/view/flats/list.jsp").forward(req,resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
