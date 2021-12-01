<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XX系统 - 房屋信息更新</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="box">
    <h3>更新房屋信息</h3>
    <form action="/contract/update" method="post">
        <table class="form-table">
            <tr hidden>
                <td>标识</td>
                <td colspan="3" class="control">
                    <input type="text" name="cid" placeholder="标识" value="${contract.cid}">
                </td>
            </tr>
            <tr>
                <td>合同号</td>
                <td colspan="3" class="control">
                    <input type="text" name="cnum" placeholder="合同号" value="${contract.cnum}">
                </td>
            </tr>
            <tr>
                <td>房屋信息</td>
                <td colspan="3" class="control">
                    <%--<input type="text" name="chid" placeholder="房屋信息" value="${contract.chid}">--%>
                    <select name="chid">
                        <%--<c:if test="${houseInfo.haddress == house.haddress 'selected':''}"></c:if>--%>
                        <c:forEach var="house1" items="${houseInfo}">
                            <option ${contract.house.haddress == house1.haddress ? 'selected':''} value="${house1.hid}">${house1.haddress}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>租户信息</td>
                <td colspan="3" class="control">
                    <%-- <input type="text" name="clid" placeholder="租户信息" value="${contract.clid}">--%>
                    <select name="clid">
                        <c:forEach var="lessee1" items="${lesseeInfo}">
                            <option ${contract.lessee.lname == lessee1.lname ? 'selected':''} value="${lessee1.lid}">${lessee1.lname}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>租赁开始时间</td>
                <td colspan="3" class="control">
                    <input type="text" name="cstartTime" placeholder="租赁开始时间" value="${contract.cstartTime}">
                </td>
            </tr>
            <tr>
                <td>租赁结束时间</td>
                <td colspan="3" class="control">
                    <input type="text" name="cendTime" placeholder="租赁结束时间" value="${contract.cendTime}">
                </td>
            </tr>
            <tr>
                <td>房租总额</td>
                <td colspan="3" class="control">
                    <input type="text" name="ctotalMoney" placeholder="房租总额" value="${contract.ctotalMoney}">
                </td>
            </tr>
            <tr>
                <td>付款方式</td>
                <td colspan="3" class="control">
                    <input type="text" name="cpayType" placeholder="付款方式" value="${contract.cpayType == 1 ? '月付':'年付'}">
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
