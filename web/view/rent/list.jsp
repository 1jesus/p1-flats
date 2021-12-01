<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XX系统 - 房租信息管理</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="box">
    <h3>房租信息管理</h3>
    <div class="actions">
        <div>
            <a class="btn btn-primary" href="/view/rent/add.jsp">添加房租信息</a>
        </div>
    </div>
    <div style="margin-top: 10px">
        <form action="/rent/list" method="get">
            <select name="field">
                <option value="rhid">房屋信息</option>
                <option value="rlid">租户信息</option>
            </select>
            <input type="text"  placeholder="输入关键词" name="keyword" value="${keyword}">
            <input type="submit" value="搜索">
        </form>
    </div>
    <table class="list">
        <tr>
            <th>标识</th>
            <th>房屋信息</th>
            <th>租户信息</th>
            <th>缴纳房租额</th>
            <th>缴纳时间</th>
            <th>操作</th>
        </tr>
        <c:forEach var="rent" items="${list}">
            <tr>
                <td>${rent.rid}</td>
                <td>${rent.rhid}</td>
                <td>${rent.rlid}</td>
                <td>${rent.rprice}</td>
                <td>${rent.rpayTime}</td>
                <td>
                    <a class="fa fa-info" title="详情" href="/rent/detail?rid=${rent.rid}"></a>
                    &nbsp;&nbsp;
                    <a class="fa fa-pencil" title="编辑" href="/rent/update?rid=${rent.rid}"></a>
                    &nbsp;&nbsp;
                    <a class="fa fa-remove" title="删除" href="#" onclick="return confirmDelete(${rent.rid})"></a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <div class="pager-info">
        <div>共有 ${total} 条记录，第 ${pageNo}/${pageCount} 页 </div>
        <div>
            <ul class="pagination">
                <c:if test="${pageNo != 1 }">
                <li class="paginate_button previous disabled }">
                    <a href="/rent/list?pageNo=${pageNo-1}&field=${field}&keyword=${keyword}">上一页</a>
                </li>
                </c:if>
                <c:forEach var="page" begin="1" end="${pageCount}"><%--  page就是当前遍历的页码 --%>
                <li class="${pageNo == page ? 'active':''}"><a href="/rent/list?pageNo=${page}&field=${field}&keyword=${keyword}">${page}</a></li>
                </c:forEach>
                <c:if test="${pageNo != pageCount}">
                <li class="paginate_button next disabled">
                    <a href="/rent/list?pageNo=${pageNo+1}&field=${field}&keyword=${keyword}">下一页</a>
                </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<script src="/lib/jquery/jquery.js"></script>
<script>
    function confirmDelete(rid) {
        if (confirm("确定要删除码？")) {
            alert('发送删除请求，刷新页面（不要异步）');
            window.location = "http://localhost:8080/rent/delete?rid="+rid;
        }
        return false;
    }
</script>
</body>
</html>
