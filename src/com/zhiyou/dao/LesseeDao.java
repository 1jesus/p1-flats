package com.zhiyou.dao;

import com.zhiyou.model.contract.Contract;
import com.zhiyou.model.lessee.Lessee;
import com.zhiyou.util.DBUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname LesseeDao
 * @Date 2021/9/14 15:23
 */
public class LesseeDao {

    public int total(String field, String keyword) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        int total = 0;
        StringBuilder sql = new StringBuilder("select count(*) 'total' from lessee ");
        if (keyword != null){
            sql.append(" where "+field+" like concat('%',?,'%') ");
        }
        try {
            ps = conn.prepareStatement(sql.toString());
            if (keyword != null){
                ps.setString(1,keyword);
            }

            resultSet = ps.executeQuery();
            while (resultSet.next()){
                total = resultSet.getInt("total");
                System.out.println("total = "+total);
            }
        } catch (SQLException e) {
            e.printStackTrace( );
        } finally {
            DBUtil.close(resultSet,ps,conn);
        }
        return total;
    }

    public List<Lessee> findLesseetAll(int start, int end, String field, String keyword) {
            Connection connection = DBUtil.getConnection();
            PreparedStatement pre = null;
            ResultSet resultSet = null;
            ArrayList<Lessee> list = new ArrayList<>();
            Lessee lessee = null;
            int i = 0;
            //基础查询
            StringBuilder sql = new StringBuilder(" select * from lessee ");
            //如果字段不为空,拼接后就是模糊查询
            if (keyword != null){
                sql.append(" where "+field+" like concat('%',?,'%') ");
                i++;
            }
            sql.append(" limit ?,? ");
            try {
                pre = connection.prepareStatement(sql.toString());
                //设置参数
                if (i != 0){
                    pre.setString(1,keyword);
                }
                pre.setInt(i+1,start);
                pre.setInt(i+2,end);

                resultSet = pre.executeQuery();
                while (resultSet.next()) {
                    int lid = resultSet.getInt("lid");
                    String lname = resultSet.getString("lname");
                    String ltel = resultSet.getString("ltel");
                    int lsex = resultSet.getInt("lsex");
                    String lnp = resultSet.getString("lnp");
                    String lidCard = resultSet.getString("lidCard");
                    Date ladTime = resultSet.getDate("ladTime");

                    //封装对象
                    lessee = new Lessee();
                    lessee.setLid(lid);
                    lessee.setLname(lname);
                    lessee.setLtel(ltel);
                    lessee.setLsex(lsex);
                    lessee.setLnp(lnp);
                    lessee.setLidCard(lidCard);
                    lessee.setLadTime(ladTime);

                    //封装完后,添加到集合
                    list.add(lessee);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(resultSet, pre, connection);
            }
            return list;
    }

    public boolean addLessee(Lessee lessee) {
        boolean isOK = false;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pre;
        SimpleDateFormat sdf = new SimpleDateFormat();
        String sql = "insert into lessee (lid,lname,ltel,lsex,lnp,lidCard,ladTime) values (?,?,?,?,?,?,?)";
        try {
            pre = conn.prepareStatement(sql);
            // sql语句参数设置值
            pre.setInt(1, lessee.getLid());
            System.out.println("lessee.getLid() = "+lessee.getLid());
            pre.setString(2, lessee.getLname());
            System.out.println("lessee.getLname() = "+lessee.getLname());
            pre.setString(3, lessee.getLtel());
            System.out.println("lessee.getLtel() = "+lessee.getLtel());
            pre.setInt(4, lessee.getLsex());
            System.out.println("lessee.getLsex() = "+lessee.getLsex());
            pre.setString(5, lessee.getLnp());
            System.out.println("lessee.getLnp() = "+lessee.getLnp());
            pre.setString(6, lessee.getLidCard());
            System.out.println("lessee.getLidCard() = "+lessee.getLidCard());
            pre.setDate(7, new Date(lessee.getLadTime().getTime()));
            System.out.println("new Date(lessee.getLadTime().getTime()) = "+new Date(lessee.getLadTime().getTime()));

            System.out.println("拼接参数后的 sql = "+sql);
            // 执行sql
            int i = pre.executeUpdate();
            if (i > 0) {
                isOK = true;
            }
        } catch (SQLException e) {
            System.out.println("插入失败");
            e.printStackTrace();
        }

        return isOK;
    }

    public void deleteById(int id) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        String sql = "delete from lessee where lid = ?";
        try {
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace( );
        } finally {
            DBUtil.close(null,ps,conn);
        }
    }

    public Lessee detailById(int id) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Lessee lessee = null;
        String sql = "select * from lessee where lid = ?";
        try {
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);

            resultSet = ps.executeQuery();
            while (resultSet.next()){
                //遍历结果
                int lid = resultSet.getInt("lid");
                String lname = resultSet.getString("lname");
                String ltel = resultSet.getString("ltel");
                int lsex = resultSet.getInt("lsex");
                String lnp = resultSet.getString("lnp");
                String lidCard = resultSet.getString("lidCard");
                Date ladTime = resultSet.getDate("ladTime");
                //封装对象
                lessee = new Lessee();
                lessee.setLid(lid);
                lessee.setLname(lname);
                lessee.setLtel(ltel);
                lessee.setLsex(lsex);
                lessee.setLnp(lnp);
                lessee.setLidCard(lidCard);
                lessee.setLadTime(ladTime);
                //打印日志
                System.out.println("日志:LesseeDao.detailById() 封装的lessee对象=" + lessee);
            }
        } catch (SQLException e) {
            e.printStackTrace( );
        } finally {
            DBUtil.close(resultSet,ps,conn);
        }
        return lessee;
    }

    public Lessee findLesseeById(int id) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Lessee lessee = null;
        String sql = "select * from lessee where lid = ?";
        try {
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);

            resultSet = ps.executeQuery();
            while (resultSet.next()){
                //遍历结果
                int lid = resultSet.getInt("lid");
                String lname = resultSet.getString("lname");
                String ltel = resultSet.getString("ltel");
                int lsex = resultSet.getInt("lsex");
                String lnp = resultSet.getString("lnp");
                String lidCard = resultSet.getString("lidCard");
                Date ladTime = resultSet.getDate("ladTime");
                //封装对象
                lessee = new Lessee();
                lessee.setLid(lid);
                lessee.setLname(lname);
                lessee.setLtel(ltel);
                lessee.setLsex(lsex);
                lessee.setLnp(lnp);
                lessee.setLidCard(lidCard);
                lessee.setLadTime(ladTime);
                //打印日志
                System.out.println("日志:LesseeDao.findLesseeById 封装的 lessee = " + lessee);
            }
        } catch (SQLException e) {
            e.printStackTrace( );
        } finally {
            DBUtil.close(resultSet,ps,conn);
        }
        return lessee;
    }

    public void updateLesseeById(Lessee lessee) {
        Connection conn = DBUtil.getConnection( );
        PreparedStatement pre = null;
        String sql = "update lessee set lid = ?,lname = ?,ltel = ?,lsex = ? ,lnp = ? , lidCard = ?, ladTime = ? where lid = ?";
        try {
            pre = conn.prepareStatement(sql);
            // 设置值
            // sql语句参数设置值
            pre.setInt(1, lessee.getLid());
            pre.setString(2, lessee.getLname());
            pre.setString(3, lessee.getLtel());
            pre.setInt(4, lessee.getLsex());
            pre.setString(5, lessee.getLnp());
            pre.setString(6, lessee.getLidCard());
            pre.setDate(7, new Date(lessee.getLadTime().getTime()));
            pre.setInt(8, lessee.getLid());
            System.out.println("拼接参数后的 sql = "+sql);
            // 执行sql
            pre.executeUpdate( );

        } catch (SQLException e) {
            System.out.println("更新失败" );
            e.printStackTrace( );
        } finally {
            DBUtil.close(pre,conn);
        }
    }
}
