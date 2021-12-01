package com.zhiyou.dao;

import com.zhiyou.model.house.House;
import com.zhiyou.model.user.Role;
import com.zhiyou.model.user.User;
import com.zhiyou.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname UserDao
 * @Date 2021/9/9 16:18
 */
public class UserDao {
    /**
     * 登录
     *
     * @param username
     * @param password
     * @return
     */
    public User findUserByUsernameAndPassword(String username, String password) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        User user = null;
        //多表联查
        String sql = " select * from user ,user_role, role where `user`.uid = user_role.uid and user_role.rid = role.roid and uname = ? and upassword = ?  ";
        try {
            pre = connection.prepareStatement(sql);
            //设置参数
            pre.setString(1, username);
            pre.setString(2, password);
            //执行
            resultSet = pre.executeQuery();
            while (resultSet.next()) {
                //遍历user对象
                int uid = resultSet.getInt("uid");
                String uname = resultSet.getString("uname");
                String upassword = resultSet.getString("upassword");
                String urealname = resultSet.getString("urealname");
                int uroid = resultSet.getInt("uroid");
                Date uaddTime = resultSet.getDate("uaddTime");

                //封装User对象
                user = new User();
                user.setUid(uid);
                user.setUname(uname);
                user.setPassword(upassword);
                user.setUrealname(urealname);
                user.setUroid(uroid);
                user.setUaddTime(uaddTime);

                //遍历role对象
                int roid = resultSet.getInt("roid");
                String roname = resultSet.getString("roname");
                String rodesc = resultSet.getString("rodesc");
                Date roaddTime = resultSet.getDate("roaddTime");

                //封装role对象
                ArrayList<Role> roles = new ArrayList<>();
                Role role = new Role();
                role.setRoid(roid);
                role.setRoname(roname);
                role.setRodesc(rodesc);
                role.setRoaddTime(roaddTime);

                //把封装好的role对象先封装到user对象的属性
                roles.add(role);

                //把封装的role对象,封装到User对象
                user.setRoleList(roles);
                //打印日志
                System.out.println("日志:UserDao.findUserByUsernameAndPassword() 封装的User对象=" + user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            DBUtil.close(resultSet, pre, connection);
        }
        return user;
    }


    /**
     * 查全部
     *
     * @param start
     * @param end
     * @param field
     * @param keyword
     * @return
     */
    public List<House> findAll(int start, int end, String field, String keyword) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        ArrayList<House> list = new ArrayList<>();
        int i = 0;
        //基础查询
        StringBuilder sql = new StringBuilder("select * from house ");
        //判断是否要加上字段模糊查询
        if (keyword != null) {
            sql.append(" where " + field + " like concat('%',?,'%')");
            i++;
        }
        sql.append(" limit ?,?");
        System.out.println("==================================拼接的sql = " + sql);
        try {
            pre = connection.prepareStatement(sql.toString());
            if (i != 0) {
                pre.setString(1, keyword);
            }
            //设置参数
            pre.setInt(1 + i, start);
            pre.setInt(2 + i, end);
            System.out.println("==================================设置参数后的sql = " + sql);
            resultSet = pre.executeQuery();
            while (resultSet.next()) {
                int hid = resultSet.getInt("hid");
                String haddress = resultSet.getString("haddress");
                String hfloor = resultSet.getString("hfloor");
                int hroomNum = resultSet.getInt("hroomNum");
                String harea = resultSet.getString("harea");
                String hdir = resultSet.getString("hdir");
                int hdeco = resultSet.getInt("hdeco");
                int hair = resultSet.getInt("hair");
                double hprice = resultSet.getDouble("hprice");
                int hrentStatus = resultSet.getInt("hrentStatus");
                String himage = resultSet.getString("himage");
                Date haddTime = resultSet.getDate("haddTime");
                Date hupdateTime = resultSet.getDate("hupdateTime");
                //封装对象
                House house = new House();
                house.setHid(hid);
                house.setHaddress(haddress);
                house.setHfloor(hfloor);
                house.setHroomNum(hroomNum);
                house.setHarea(harea);
                house.setHdir(hdir);
                house.setHdeco(hdeco);
                house.setHair(hair);
                house.setHprice(hprice);
                house.setHrentStatus(hrentStatus);
                house.setHimage(himage);
                house.setHaddTime(haddTime);
                house.setHupdateTime(hupdateTime);
                System.out.println("日志:UserDao.findAll() 封装的house对象 = " + house);

                //封装完后,添加到集合
                list.add(house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, pre, connection);
        }
        return list;
    }

    /**
     * 添加
     *
     * @param house
     * @return
     */
    public boolean addHouse(House house) {
        boolean isOK = false;
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps;
        String sql = "insert into house (haddress,hfloor,hroomNum,harea,hdir,hdeco,hair,hprice,hrentStatus) values(?,?,?,?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            // sql语句参数设置值
            ps.setString(1, house.getHaddress());
            ps.setString(2, house.getHfloor());
            ps.setInt(3, house.getHroomNum());
            ps.setString(4, house.getHarea());
            ps.setString(5, house.getHdir());
            ps.setInt(6, house.getHdeco());
            ps.setInt(7, house.getHair());
            ps.setDouble(8, house.getHprice());
            ps.setInt(9, house.getHrentStatus());

            // 执行sql
            int i = ps.executeUpdate();
            if (i > 0) {
                isOK = true;
            }
        } catch (SQLException e) {
            System.out.println("插入失败");
            e.printStackTrace();
        }

        return isOK;
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(int id) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        String sql = "delete from house where hid = ?";
        try {
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(null, ps, conn);
        }
    }

    /**
     * 详情
     *
     * @param id
     */
    public House detailById(int id) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        House house = null;
        String sql = "select * from house where hid = ?";
        try {
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, id);

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                String haddress = resultSet.getString("haddress");
                String hfloor = resultSet.getString("hfloor");
                int hroomNum = resultSet.getInt("hroomNum");
                String harea = resultSet.getString("harea");
                String hdir = resultSet.getString("hdir");
                int hdeco = resultSet.getInt("hdeco");
                int hair = resultSet.getInt("hair");
                double hprice = resultSet.getDouble("hprice");
                int hrentStatus = resultSet.getInt("hrentStatus");
                Date haddTime = resultSet.getDate("haddTime");
                Date hupdateTime = resultSet.getDate("hupdateTime");

                //封装对象
                house = new House();
                house.setHaddress(haddress);
                house.setHfloor(hfloor);
                house.setHroomNum(hroomNum);
                house.setHarea(harea);
                house.setHdir(hdir);
                house.setHdeco(hdeco);
                house.setHair(hair);
                house.setHprice(hprice);
                house.setHrentStatus(hrentStatus);
                house.setHaddTime(haddTime);
                house.setHupdateTime(hupdateTime);

                //打印日志
                System.out.println("日志:UserDao.detailById() 封装的house对象=" + house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, ps, conn);
        }
        return house;
    }

    /**
     * 编辑
     * 更新前,查询数据
     *
     * @param id
     * @return
     */
    public House findHouseById(int id) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        House house = null;
        String sql = "select * from house where hid = ?";
        try {
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, id);

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int hid = resultSet.getInt("hid");
                String haddress = resultSet.getString("haddress");
                String hfloor = resultSet.getString("hfloor");
                int hroomNum = resultSet.getInt("hroomNum");
                String harea = resultSet.getString("harea");
                String hdir = resultSet.getString("hdir");
                int hdeco = resultSet.getInt("hdeco");
                int hair = resultSet.getInt("hair");
                double hprice = resultSet.getDouble("hprice");
                int hrentStatus = resultSet.getInt("hrentStatus");
                Date haddTime = resultSet.getDate("haddTime");
                Date hupdateTime = resultSet.getDate("hupdateTime");

                //封装对象
                house = new House();
                house.setHid(hid);
                house.setHaddress(haddress);
                house.setHfloor(hfloor);
                house.setHroomNum(hroomNum);
                house.setHarea(harea);
                house.setHdir(hdir);
                house.setHdeco(hdeco);
                house.setHair(hair);
                house.setHprice(hprice);
                house.setHrentStatus(hrentStatus);
                house.setHaddTime(haddTime);
                house.setHupdateTime(hupdateTime);

                //打印日志
                System.out.println("日志:UserDao.findHouseById() 封装的house对象=" + house);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return house;
    }

    /**
     * 编辑
     * 执行更新,把传过来数据设置(set)一下
     *
     * @param house
     */
    public void updateHouseById(House house) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        String sql = "update house set haddress = ?,hfloor = ?,hroomNum = ?,harea = ? ,hdir = ? , hdeco = ?, hair = ?, hprice = ?, hrentStatus = ? where hid = ?";
        try {
            ps = conn.prepareStatement(sql);
            // 设置值
            ps.setString(1, house.getHaddress());
            ps.setString(2, house.getHfloor());
            ps.setInt(3, house.getHroomNum());
            ps.setString(4, house.getHarea());
            ps.setString(5, house.getHdir());
            ps.setInt(6, house.getHdeco());
            ps.setInt(7, house.getHair());
            ps.setDouble(8, house.getHprice());
            ps.setInt(9, house.getHrentStatus());
            ps.setInt(10, house.getHid());

            // 执行sql
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("更新失败");
            e.printStackTrace();
        } finally {
            DBUtil.close(ps, conn);
        }
    }

    /**
     * 查询有多少条数据
     *
     * @param field
     * @param keyword
     * @return
     */
    public int total(String field, String keyword) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        //查询数据总条数
        int total = 0;
        StringBuilder sql = new StringBuilder("select count(*) 'total' from house ");
        //根据传过来的参数判断是否要模糊查询
        if (keyword != null) {
            sql.append(" where " + field + " like concat('%',?,'%') ");
        }
        try {
            pre = connection.prepareStatement(sql.toString());

            //如果关键字不为null ,加上关键字即为模糊查询,如果没有关键字,则不拼接,则还是简单的总数查询
            if (keyword != null) {
                pre.setString(1, keyword);
            }

            //执行sql
            resultSet = pre.executeQuery();
            if (resultSet.next()) {
                total = resultSet.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, pre, connection);
        }
        System.out.println("日志:UserDao.total() total=" + total);
        return total;
    }
}
