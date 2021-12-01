<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XX系统 - 租户信息添加</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="box">
    <h3>添加租户信息</h3>
    <form action="/lessee/add" method="post">
        <table class="form-table">
            <tr>
                <td>标识</td>
                <td colspan="3" class="control">
                    <input type="text" name="lid" placeholder="填写标识">
                </td>
            </tr>
            <tr>
                <td>租户姓名</td>
                <td colspan="3" class="control">
                    <input type="text" name="lname" placeholder="租户姓名">
                </td>
            </tr>
            <tr>
                <td>手机号码</td>
                <td colspan="3" class="control">
                    <input type="text" name="ltel" placeholder="手机号码">
                </td>
            </tr>
            <tr>
                <td>性别</td>
                <td colspan="3" class="control">
                    <select name="lsex">
                        <option>性别</option>
                        <option value="1">男</option>
                        <option value="2">女</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>籍贯</td>
                <td colspan="3" class="control">
                    <input type="text" name="lnp" placeholder="籍贯">
                </td>
            </tr>
            <tr>
                <td>身份证号</td>
                <td colspan="3" class="control">
                    <input type="text" name="lidCard" placeholder="身份证号">
                </td>
            </tr>
            <tr>
                <td>添加时间</td>
                <td colspan="3" class="control">
                    <input type="text" name="ladTime" placeholder="添加时间">
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
