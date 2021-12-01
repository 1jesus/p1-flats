<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XX系统 - 房租信息详情查看</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="box">
    <h3>房租信息详情</h3>
    <form action="#" method="post">
        <table class="form-table">
            <tr>
                <td class="label">标识</td>
                <td class="detail">
                    ${rent.rid}
                </td>
            </tr>
            <tr>
                <td class="label">房屋信息</td>
                <td colspan="3" class="detail">
                    ${rent.rhid}
                </td>
            </tr>
            <tr>
                <td class="label">租户信息</td>
                <td colspan="3" class="detail">
                    ${rent.rlid}
                </td>
            </tr>
            <tr>
                <td class="label">缴纳房租额</td>
                <td colspan="3" class="detail">
                    ${rent.rprice}
                </td>
            </tr>
            <tr>
                <td class="label">缴纳时间</td>
                <td colspan="3" class="detail">
                    ${rent.rpayTime}
                </td>
            </tr>
        </table>
        <div class="buttons">
            <a class="btn btn-default" href="javascript:history.go(-1)">返回</a>
        </div>
    </form>
</div>
</body>
</html>
