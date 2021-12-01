package com.zhiyou.dao;

import com.zhiyou.model.lessee.Lessee;
import com.zhiyou.model.rent.Rent;
import com.zhiyou.util.DBUtil;

import javax.sound.midi.SoundbankResource;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname RentDao
 * @Date 2021/9/14 20:17
 */
public class RentDao {
    public int total(String field, String keyword) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        int total = 0;
        StringBuilder sql = new StringBuilder("select count(*) 'total' from rent ");
        if (keyword != null) {
            sql.append(" where " + field + " like concat('%',?,'%') ");
        }
        try {
            ps = conn.prepareStatement(sql.toString());
            if (keyword != null) {
                ps.setString(1, keyword);
            }

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                total = resultSet.getInt("total");
                System.out.println("total = " + total);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, ps, conn);
        }
        return total;
    }

    public List<Rent> findRentAll(int start, int end, String field, String keyword) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        ArrayList<Rent> list = new ArrayList<>();
        Rent rent = null;
        int i = 0;
        //基础查询
        StringBuilder sql = new StringBuilder(" select * from rent ");
        //如果字段不为空,拼接后就是模糊查询
        if (keyword != null) {
            sql.append(" where " + field + " like concat('%',?,'%') ");
            i++;
        }
        sql.append(" limit ?,? ");
        try {
            pre = connection.prepareStatement(sql.toString());
            //设置参数
            if (i != 0) {
                pre.setString(1, keyword);
            }
            pre.setInt(i + 1, start);
            pre.setInt(i + 2, end);

            resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int rid = resultSet.getInt("rid");
                int rhid = resultSet.getInt("rhid");
                System.out.println("遍历 result 得到 rhid = "+rhid);
                int rlid = resultSet.getInt("rlid");
                String rprice = resultSet.getString("rprice");
                Date rpayTime = resultSet.getDate("rpayTime");

                //封装对象
                rent = new Rent();
                rent.setRid(rid);
                rent.setRhid(rhid);
                System.out.println("封装的 rhid = "+rhid);
                rent.setRlid(rlid);
                rent.setRprice(Double.parseDouble(rprice));
                rent.setRpayTime(rpayTime);

                //封装完后,添加到集合
                list.add(rent);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, pre, connection);
        }
        return list;
    }

    public boolean addRent(Rent rent) {
        boolean isOK = false;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pre;
        SimpleDateFormat sdf = new SimpleDateFormat();
        String sql = "insert into rent (rid,rhid,rlid,rprice,rpayTime) values (?,?,?,?,?)";
        try {
            pre = conn.prepareStatement(sql);
            // sql语句参数设置值
            pre.setInt(1, rent.getRid());
            System.out.println("rent.getRid() = "+rent.getRid());

            pre.setInt(2, rent.getRhid());
            System.out.println("rent.getRhid() = "+rent.getRhid());

            pre.setInt(3, rent.getRlid());
            System.out.println("rent.getRhid() = "+rent.getRhid());

            pre.setDouble(4, rent.getRprice());
            System.out.println("rent.getRprice() = "+rent.getRprice());

            pre.setDate(5, new Date(rent.getRpayTime().getTime()));
            System.out.println("new Date(rent.getRpayTime().getTime()) = "+new Date(rent.getRpayTime().getTime()));

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

    public Rent detailById(int id) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Rent rent = null;
        String sql = "select * from rent where rid = ?";
        try {
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);

            resultSet = ps.executeQuery();
            while (resultSet.next()){
                //遍历结果
                int rid = resultSet.getInt("rid");
                int rhid = resultSet.getInt("rhid");
                int rlid = resultSet.getInt("rlid");
                String rprice = resultSet.getString("rprice");
                Date rpayTime = resultSet.getDate("rpayTime");

                //封装对象
                rent = new Rent();
                rent.setRid(rid);
                rent.setRlid(rhid);
                rent.setRlid(rlid);
                rent.setRprice(Double.parseDouble(rprice));
                rent.setRpayTime(rpayTime);
                //打印日志
                System.out.println("日志:RentDao.detailById() 封装的rent对象=" + rent);
            }
        } catch (SQLException e) {
            e.printStackTrace( );
        } finally {
            DBUtil.close(resultSet,ps,conn);
        }
        return rent;
    }

    public void deleteById(int id) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        String sql = "delete from rent where rid = ?";
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

    public Rent findeRentById(int id) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Rent rent = null;
        String sql = "select * from rent where rid = ?";
        try {
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1,id);

            resultSet = ps.executeQuery();
            while (resultSet.next()){
                //遍历结果
                int rid = resultSet.getInt("rid");
                int rhid = resultSet.getInt("rhid");
                System.out.println("遍历 result 得到 rhid = "+rhid);
                int rlid = resultSet.getInt("rlid");
                String rprice = resultSet.getString("rprice");
                Date rpayTime = resultSet.getDate("rpayTime");

                //封装对象
                rent = new Rent();
                rent.setRid(rid);
                rent.setRhid(rhid);
                System.out.println("封装的 rhid = "+rhid);
                rent.setRlid(rlid);
                rent.setRprice(Double.parseDouble(rprice));
                rent.setRpayTime(rpayTime);
                //打印日志
                System.out.println("日志:RentDao.detailById() 封装的rent对象=" + rent);
            }
        } catch (SQLException e) {
            e.printStackTrace( );
        } finally {
            DBUtil.close(resultSet,ps,conn);
        }
        return rent;
    }
    public void updateRentById(Rent rent) {
        Connection conn = DBUtil.getConnection( );
        PreparedStatement pre = null;
        String sql = "update rent set rid = ?,rhid = ?,rlid = ?,rprice = ? ,rpayTime = ? where rid = ?";
        try {
            pre = conn.prepareStatement(sql);
            // 设置值
            pre.setInt(1, rent.getRid());
            pre.setInt(2, rent.getRhid());
            pre.setInt(3, rent.getRlid());
            pre.setDouble(4, rent.getRprice());
            pre.setDate(5, new Date(rent.getRpayTime().getTime()));
            pre.setInt(6, rent.getRid());
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
