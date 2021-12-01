package com.zhiyou.dao;

import com.zhiyou.model.contract.Contract;
import com.zhiyou.model.house.House;
import com.zhiyou.model.lessee.Lessee;
import com.zhiyou.util.DBUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ContractDao
 * @Date 2021/9/13 16:57
 */
public class ContractDao {
    /**
     * 查询全部
     *
     * @param start
     * @param end
     * @param field
     * @param keyword
     * @return
     */
    public List<Contract> findContractAll(int start, int end, String field, String keyword) {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        ArrayList<Contract> list = new ArrayList<>();
        Contract contract = null;
        int i = 0;
        //基础查询
        StringBuilder sql = new StringBuilder(" select * from contract,lessee,house where ");
        //如果字段不为空,拼接后就是模糊查询
        if (keyword != null) {
            sql.append(" " + field + " like concat('%',?,'%') and ");
            i++;
        }
        sql.append(" contract.chid = house.hid and contract.clid = lessee.lid limit ?,? ");
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

                //遍历contract
                int cid = resultSet.getInt("cid");
                String cnum = resultSet.getString("cnum");
                int chid = resultSet.getInt("chid");
                int clid = resultSet.getInt("clid");
                Date cstartTime = resultSet.getDate("cstartTime");
                Date cendTime = resultSet.getDate("cendTime");
                String ctotalMoney = resultSet.getString("ctotalMoney");
                int cpayType = resultSet.getInt("cpayType");

                //遍历house
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

                //遍历lessee
                int lid = resultSet.getInt("lid");
                String lname = resultSet.getString("lname");
                String ltel = resultSet.getString("ltel");
                int lsex = resultSet.getInt("lsex");
                String lnp = resultSet.getString("lnp");
                String lidCard = resultSet.getString("lidCard");
                Date ladTime = resultSet.getDate("ladTime");

                //封装contract对象
                contract = new Contract();
                contract.setCid(cid);
                contract.setCnum(cnum);
                contract.setChid(chid);
                contract.setClid(clid);
                contract.setCstartTime(cstartTime);
                contract.setCendTime(cendTime);
                contract.setCtotalMoney(ctotalMoney);
                contract.setCpayType(cpayType);

                //封装house对象
                House house = new House();
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

                //封装lessee对象
                Lessee lessee = new Lessee();
                lessee.setLid(lid);
                lessee.setLname(lname);
                lessee.setLtel(ltel);
                lessee.setLsex(lsex);
                lessee.setLnp(lnp);
                lessee.setLidCard(lidCard);
                lessee.setLadTime(ladTime);


                //把封装完后的house对象,存入contract对象中
                contract.setHouse(house);
                //把封装完后的lessee对象,存入contract对象中
                contract.setLessee(lessee);

                //封装完后,添加到集合
                list.add(contract);
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
     * @param contract
     * @return
     */
    public boolean addContract(Contract contract) {
        boolean isOK = false;
        Connection conn = DBUtil.getConnection();
        PreparedStatement pre;
        SimpleDateFormat sdf = new SimpleDateFormat();
        String sql = "insert into contract (cid,cnum,chid,clid,cstartTime,cendTime,ctotalMoney,cpayType) values(?,?,?,?,?,?,?,?)";
        try {
            pre = conn.prepareStatement(sql);
            // sql语句参数设置值
            pre.setInt(1, contract.getCid());
            pre.setString(2, contract.getCnum());
            pre.setInt(3, contract.getChid());
            pre.setInt(4, contract.getClid());
            pre.setDate(5, new Date(contract.getCstartTime().getTime()));
            pre.setDate(6, new Date(contract.getCendTime().getTime()));
            pre.setString(7, contract.getCtotalMoney());
            pre.setInt(8, contract.getCpayType());

            System.out.println("拼接参数后的 sql = " + sql);
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

    /**
     * 删除
     *
     * @param cid
     */
    public void deleteById(int cid) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        String sql = "delete from contract where cid = ?";
        try {
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, cid);
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
     * @return
     */
    public Contract detailById(int id) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Contract contract =null;
        String sql = "select * from contract,lessee,house where cid = ? and contract.chid = house.hid and contract.clid = lessee.lid";
        try {
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                //遍历contract
                int cid = resultSet.getInt("cid");
                String cnum = resultSet.getString("cnum");
                int chid = resultSet.getInt("chid");
                int clid = resultSet.getInt("clid");
                Date cstartTime = resultSet.getDate("cstartTime");
                Date cendTime = resultSet.getDate("cendTime");
                String ctotalMoney = resultSet.getString("ctotalMoney");
                int cpayType = resultSet.getInt("cpayType");
                //封装contract对象
                contract = new Contract();
                contract.setCid(cid);
                contract.setCnum(cnum);
                contract.setChid(chid);
                contract.setClid(clid);
                contract.setCstartTime(cstartTime);
                contract.setCendTime(cendTime);
                contract.setCtotalMoney(ctotalMoney);
                contract.setCpayType(cpayType);
                //遍历house
                String haddress = resultSet.getString("haddress");

                //遍历lessee
                int lid = resultSet.getInt("lid");
                String lname = resultSet.getString("lname");

                //封装house对象
                House house = new House();
                house.setHaddress(haddress);


                //封装lessee对象
                Lessee lessee = new Lessee();
                lessee.setLid(lid);
                lessee.setLname(lname);


                //把封装完后的house对象,存入contract对象中
                contract.setHouse(house);
                //把封装完后的lessee对象,存入contract对象中
                contract.setLessee(lessee);

                //打印日志
                System.out.println("日志:ContractDao.detailById() 封装的contract对象=" + contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, ps, conn);
        }
        return contract;
    }
    /**
     * 更新
     * 更新前的查询
     *
     * @param id
     * @return
     */
    public Contract findContractById(int id) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Contract contract =null;
        String sql = "select * from contract,lessee,house where cid = ? and contract.chid = house.hid and contract.clid = lessee.lid";
        try {
            ps = conn.prepareStatement(sql);
            //设置参数
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                //遍历contract
                int cid = resultSet.getInt("cid");
                String cnum = resultSet.getString("cnum");
                int chid = resultSet.getInt("chid");
                int clid = resultSet.getInt("clid");
                Date cstartTime = resultSet.getDate("cstartTime");
                Date cendTime = resultSet.getDate("cendTime");
                String ctotalMoney = resultSet.getString("ctotalMoney");
                int cpayType = resultSet.getInt("cpayType");
                //封装contract对象
                contract = new Contract();
                contract.setCid(cid);
                contract.setCnum(cnum);
                contract.setChid(chid);
                contract.setClid(clid);
                contract.setCstartTime(cstartTime);
                contract.setCendTime(cendTime);
                contract.setCtotalMoney(ctotalMoney);
                contract.setCpayType(cpayType);
                //遍历house
                String haddress = resultSet.getString("haddress");

                //遍历lessee
                int lid = resultSet.getInt("lid");
                String lname = resultSet.getString("lname");

                //封装house对象
                House house = new House();
                house.setHaddress(haddress);


                //封装lessee对象
                Lessee lessee = new Lessee();
                lessee.setLid(lid);
                lessee.setLname(lname);


                //把封装完后的house对象,存入contract对象中
                contract.setHouse(house);
                //把封装完后的lessee对象,存入contract对象中
                contract.setLessee(lessee);

                //打印日志
                System.out.println("日志:ContractDao.findContractById() 封装的contract对象=" + contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, ps, conn);
        }
        return contract;
    }

    /**
     * 执行更新
     *
     * @param contract
     */
    public void updateContractById(Contract contract) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement pre = null;
        String sql = "update contract set cid = ?,cnum = ?,chid = ?,clid = ? , cstartTime = ?, cendTime = ?, ctotalMoney = ?, cpayType = ? where cid = ?";
        try {
            pre = conn.prepareStatement(sql);
            // 设置值
            pre.setInt(1, contract.getCid());
            pre.setString(2, contract.getCnum());
            pre.setInt(3, contract.getChid());
            pre.setInt(4, contract.getClid());
            pre.setDate(5, new Date(contract.getCstartTime().getTime()));
            pre.setDate(6, new Date(contract.getCendTime().getTime()));
            pre.setString(7, contract.getCtotalMoney());
            pre.setInt(8, contract.getCpayType());
            pre.setInt(9, contract.getCid());

            System.out.println("拼接参数后的 sql = " + sql);
            System.out.println("set后的 cstartTime = " + new Date(contract.getCstartTime().getTime()));
            System.out.println("set后的 cendTime = " + new Date(contract.getCendTime().getTime()));

            // 执行sql
            pre.executeUpdate();

        } catch (SQLException e) {
            System.out.println("更新失败");
            e.printStackTrace();
        } finally {
            DBUtil.close(pre, conn);
        }
    }

    public int total(String field, String keyword) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        int total = 0;
        StringBuilder sql = new StringBuilder(" select count(*) 'total' from contract , lessee , house where ");
        //模糊查询字段
        if (keyword != null) {
            sql.append(" " + field + " like concat('%',?,'%') and ");
        }
        //因为内连接多表联查,不会保留不符合条件的数据
        //分页后为了,能有正确的分页数据,拼接上多表联查部分
        sql.append("contract.chid = house.hid and contract.clid = lessee.lid ");
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

    public ArrayList<Contract> findMultiTableByUid() {
        Connection connection = DBUtil.getConnection();
        PreparedStatement pre = null;
        ResultSet resultSet = null;
        ArrayList<Contract> list = new ArrayList<>();
        Contract contract = null;
        String sql = " select * from contract,house,lessee where contract.chid = house.hid and contract.clid = lessee.lid ";
        try {
            pre = connection.prepareStatement(sql);
            resultSet = pre.executeQuery();
            while (resultSet.next()) {
                //遍历contract
                int cid = resultSet.getInt("cid");
                String cnum = resultSet.getString("cnum");
                int chid = resultSet.getInt("chid");
                int clid = resultSet.getInt("clid");
                Date cstartTime = resultSet.getDate("cstartTime");
                Date cendTime = resultSet.getDate("cendTime");
                String ctotalMoney = resultSet.getString("ctotalMoney");
                int cpayType = resultSet.getInt("cpayType");

                //遍历house
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

                //遍历lessee
                int lid = resultSet.getInt("lid");
                String lname = resultSet.getString("lname");
                String ltel = resultSet.getString("ltel");
                int lsex = resultSet.getInt("lsex");
                String lnp = resultSet.getString("lnp");
                String lidCard = resultSet.getString("lidCard");
                Date ladTime = resultSet.getDate("ladTime");

                //封装contract对象
                contract = new Contract();
                contract.setCid(cid);
                contract.setCnum(cnum);
                contract.setChid(chid);
                contract.setClid(clid);
                contract.setCstartTime(cstartTime);
                contract.setCendTime(cendTime);
                contract.setCtotalMoney(ctotalMoney);
                contract.setCpayType(cpayType);

                //封装house对象
                House house = new House();
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

                //封装对象
                Lessee lessee = new Lessee();
                lessee.setLid(lid);
                lessee.setLname(lname);
                lessee.setLtel(ltel);
                lessee.setLsex(lsex);
                lessee.setLnp(lnp);
                lessee.setLidCard(lidCard);
                lessee.setLadTime(ladTime);



                //把封装完后的house对象,存入contract对象中
                contract.setHouse(house);
                //把封装完后的lessee对象,存入contract对象中
                contract.setLessee(lessee);


                list.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, pre, connection);
        }
        return list;
    }

    //查询关联表house的hid和haddress字段的信息
    public ArrayList<House> getHouseInfo() {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        ArrayList<House> houseList = new ArrayList<>();
        String sql = "select hid,haddress from house";
        try {
            ps = conn.prepareStatement(sql);

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                //遍历结果
                int hid = resultSet.getInt("hid");
                String haddress = resultSet.getString("haddress");

                //封装对象
                House house = new House();
                house.setHid(hid);
                house.setHaddress(haddress);

                //封装集合
                houseList.add(house);

                //打印日志
                System.out.println("日志: 查询关联表house的hid和haddress字段的信息    封装的houseList对象=" + houseList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, ps, conn);
        }
        return houseList;
    }

    //查询关联表lessee的hid和haddress字段的信息
    public ArrayList<Lessee> getLesseeInfo() {
        Connection conn = DBUtil.getConnection();
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        ArrayList<Lessee> lesseeList = new ArrayList();
        String sql = "select lid,lname from lessee";
        try {
            ps = conn.prepareStatement(sql);

            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                //遍历结果
                int lid = resultSet.getInt("lid");
                String lname = resultSet.getString("lname");

                //封装对象
                Lessee lessee = new Lessee();
                lessee.setLid(lid);
                lessee.setLname(lname);

                //封装集合
                lesseeList.add(lessee);

                //打印日志
                System.out.println("日志: 查询关联表lessee的hid和haddress字段的信息    封装的lesseeList对象=" + lesseeList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(resultSet, ps, conn);
        }
        return lesseeList;
    }
}
