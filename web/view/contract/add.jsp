<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XX系统 - 房屋信息添加</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="box">
    <h3>添加合同信息</h3>
    <form action="/contract/add" method="post">
        <table class="form-table">
            <tr>
                <td>标识</td>
                <td colspan="3" class="control">
                    <input type="text" name="cid" placeholder="填写标识">
                </td>
            </tr>
            <tr>
                <td>合同号</td>
                <td colspan="3" class="control">
                    <input type="text" name="cnum" placeholder="合同号">
                </td>
            </tr>
            <tr>
                <td>房屋信息</td>
                <td colspan="3" class="control">
                    <%--<input type="text" name="chid" placeholder="房屋信息">--%>
                    <select name="chid">
                        <c:forEach var="house" items="${houseInfo}">
                            <option value="${house.hid}">${house.haddress}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>租户信息</td>
                <td colspan="3" class="control">
                    <%--<input type="text" name="clid" placeholder="租户信息">--%>
                        <select name="clid">
                            <c:forEach var="lessee" items="${lesseeInfo}">
                                <option value="${lessee.lid}">${lessee.lname}</option>
                            </c:forEach>
                        </select>
                </td>
            </tr>
            <tr>
                <td>租赁开始时间</td>
                <td colspan="3" class="control">
                    <input type="text" name="cstartTime" placeholder="租赁开始时间">
                </td>
            </tr>
            <tr>
                <td>租赁结束时间</td>
                <td colspan="3" class="control">
                    <input type="text" name="cendTime" placeholder="租赁结束时间">
                </td>
            </tr>
            <tr>
                <td>房租总额</td>
                <td colspan="3" class="control">
                    <input type="text" name="ctotalMoney" placeholder="房租总额">
                </td>
            </tr>
            <tr>
                <td>付款方式</td>
                <td colspan="3" class="control">
                    <select name="cpayType">
                        <option>付款方式</option>
                        <option value="1">月付</option>
                        <option value="2">年付</option>
                    </select>
                </td>
            </tr>

        </table>
        <div class="buttons">
            <input class="btn btn-primary va-bottom" type="submit" value="保存">&nbsp;&nbsp;
            <a class="btn btn-default" href="javascript:history.go(-1)">返回</a>
        </div>
    </form>
</div>
</body>
</html>
