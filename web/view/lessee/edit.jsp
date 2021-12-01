<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XX系统 - 租户信息更新</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="box">
    <h3>更新租户信息</h3>
    <form action="/lessee/update" method="post">
        <table class="form-table">
            <tr hidden>
                <td>标识</td>
                <td colspan="3" class="control">
                    <input type="text" name="lid" placeholder="标识" value="${lessee.lid}">
                </td>
            </tr>
            <tr>
                <td>租户姓名</td>
                <td colspan="3" class="control">
                    <input type="text" name="lname" placeholder="租户姓名" value="${lessee.lname}">
                </td>
            </tr>
            <tr>
                <td>手机号码</td>
                <td colspan="3" class="control">
                    <input type="text" name="ltel" placeholder="手机号码" value="${lessee.ltel}">
                </td>
            </tr>
            <tr>
                <td>性别</td>
                <td colspan="3" class="control">
                    <input type="text" name="lsex" placeholder="性别" value="${lessee.lsex == 1 ? '男' : '女'}">
                </td>
            </tr>
            <tr>
                <td>籍贯</td>
                <td colspan="3" class="control">
                    <input type="text" name="lnp" placeholder="籍贯" value="${lessee.lnp}">
                </td>
            </tr>
            <tr>
                <td>身份证号</td>
                <td colspan="3" class="control">
                    <input type="text" name="lidCard" placeholder="身份证号" value="${lessee.lidCard}">
                </td>
            </tr>
            <tr>
                <td>添加时间</td>
                <td colspan="3" class="control">
                    <input type="text" name="ladTime" placeholder="添加时间" value="${lessee.ladTime}">
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
