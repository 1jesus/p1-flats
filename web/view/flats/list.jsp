<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>XX系统 - 房屋信息管理</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="box">
    <h3>房屋信息管理</h3>
    <div class="actions">
        <div>
            <a class="btn btn-primary" href="/view/flats/add.jsp">添加房屋信息</a>
        </div>
    </div>

    <div style="margin-top: 10px" >
        <form action="/house/list" method="get">
            <select name="field">
                <option value="haddress">地址</option>
                <option value="hdir">朝向</option>
            </select>
            <input type="text" placeholder="输入关键字" name="keyword" value="${keyword}">
            <input type="submit" value="搜索">
        </form>
    </div>

    <table class="list">
        <tr>
            <th>标识</th>
            <th>地址</th>
            <th>楼层</th>
            <th>房间号</th>
            <th>面积</th>
            <th>朝向</th>
            <th>装修</th>
            <th>是否双气</th>
            <th>出租价格</th>
            <th>出租状态</th>
            <th>房屋图片</th>
            <th>添加时间</th>
            <th>更新时间</th>
            <th>操作</th>
        </tr>
        <c:forEach var="house" items="${list}">
            <tr>
                <td>${house.hid}</td>
                <td>${house.haddress}</td>
                <td>${house.hfloor}</td>
                <td>${house.hroomNum}</td>
                <td>${house.harea}</td>
                <td>${house.hdir}</td>
                <td>${house.hdeco ==1 ? '精装':'简装'}</td>
                <td>${house.hair ==1 ? '双气':'单气'}</td>
                <td>${house.hprice}</td>
                <td>${house.hrentStatus == 1 ? '已出租' : '待出租'}</td>
                <td>${house.himage}</td>
                <td>${house.haddTime}</td>
                <td>${house.hupdateTime}</td>
                <td>
                    <a class="fa fa-info" title="详情" href="/house/detail?id=${house.hid}"></a>
                    &nbsp;&nbsp;
                    <a class="fa fa-pencil" title="编辑" href="/house/update?hid=${house.hid}"></a>
                    &nbsp;&nbsp;
                    <a class="fa fa-remove" title="删除" href="#" onclick="return confirmDelete(${house.hid})"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="pager-info">
        <div>共有 ${total} 条记录，第 ${pageNo}/${pageCount} 页</div>
        <div>
            <ul class="pagination">
                <c:if test="${pageNo != 1}">
                <li class="paginate_button previous disabled">
                    <a href="/house/list?pageNo=${pageNo-1}&field=${field}&keyword=${keyword}">上一页</a>
                </li>
                </c:if>
                <c:forEach var="page" begin="1" end="${pageCount}">
                <li class="${pageNo == page ? 'active':''}"><a href="/house/list?pageNo=${page}&field=${field}&keyword=${keyword}">${page}</a></li>
                </c:forEach>
                <c:if test="${pageNo != pageCount}">
                <li class="paginate_button next disabled">
                    <a href="/house/list?pageNo=${pageNo+1}&field=${field}&keyword=${keyword}">下一页</a>
                </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<script src="../../lib/jquery/jquery.js"></script>
<script>
    function confirmDelete(hid) {
        var isDelete = confirm("确定要删除码？");
        if (isDelete) {
            alert('发送删除请求，刷新页面（不要异步）');
            window.location = "http://localhost:8080/house/delete?hid="+hid;
        }
        return false;
    }
</script>
</body>
</html>
