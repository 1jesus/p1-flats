<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XX系统 - 租户信息详情查看</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="box">
    <h3>租户信息详情</h3>
    <form action="#" method="post">
        <table class="form-table">
            <tr>
                <td class="label">标识</td>
                <td class="detail">
                    ${lessee.lid}
                </td>
            </tr>
            <tr>
                <td class="label">租户姓名</td>
                <td colspan="3" class="detail">
                    ${lessee.lname}
                </td>
            </tr>
            <tr>
                <td class="label">手机号码</td>
                <td colspan="3" class="detail">
                    ${lessee.ltel}
                </td>
            </tr>
            <tr>
                <td class="label">性别</td>
                <td colspan="3" class="detail">
                    ${lessee.lsex == 1 ? '男' : '女'}
                </td>
            </tr>
            <tr>
                <td class="label">籍贯</td>
                <td colspan="3" class="detail">
                    ${lessee.lnp}
                </td>
            </tr>
            <tr>
                <td class="label">身份证号</td>
                <td colspan="3" class="detail">
                    ${lessee.lidCard}
                </td>
            </tr>
            <tr>
                <td class="label">添加时间</td>
                <td colspan="3" class="detail">
                    ${lessee.ladTime}
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
