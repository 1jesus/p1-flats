<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XX系统 - 房租信息添加</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="box">
    <h3>添加房租信息</h3>
    <form action="/rent/add" method="post">
        <table class="form-table">
            <tr>
                <td>标识</td>
                <td colspan="3" class="control">
                    <input type="text" name="rid" placeholder="填写标识">
                </td>
            </tr>
            <tr>
                <td>房屋信息</td>
                <td colspan="3" class="control">
                    <input type="text" name="rhid" placeholder="房屋信息">
                </td>
            </tr>
            <tr>
                <td>租户信息</td>
                <td colspan="3" class="control">
                    <input type="text" name="rlid" placeholder="租户信息">
                </td>
            </tr>
            <tr>
                <td>缴纳房租额</td>
                <td colspan="3" class="control">
                    <input type="text" name="rprice" placeholder="缴纳房租额">
                </td>
            </tr>
            <tr>
                <td>缴纳时间</td>
                <td colspan="3" class="control">
                    <input type="text" name="rpayTime" placeholder="缴纳时间">
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
