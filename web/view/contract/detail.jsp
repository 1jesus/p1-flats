<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>XX系统 - 房屋信息详情查看</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="box">
    <h3>合同信息详情</h3>
    <form action="#" method="post">
        <table class="form-table">
            <tr>
                <td class="label">标识</td>
                <td class="detail">
                    ${contract.cid}
                </td>
            </tr>
            <tr>
                <td class="label">合同号</td>
                <td colspan="3" class="detail">
                    ${contract.cnum}
                </td>
            </tr>
            <tr>
                <td class="label">房屋信息</td>
                <td colspan="3" class="detail">
                    ${contract.house.haddress}
                </td>
            </tr>
            <tr>
                <td class="label">租户信息</td>
                <td colspan="3" class="detail">
                    ${contract.lessee.lname}
                </td>
            </tr>
            <tr>
                <td class="label">租赁开始时间</td>
                <td colspan="3" class="detail">
                    ${contract.cstartTime}
                </td>
            </tr>
            <tr>
                <td class="label">租赁结束时间</td>
                <td colspan="3" class="detail">
                    ${contract.cendTime}
                </td>
            </tr>
            <tr>
                <td class="label">房租总额</td>
                <td colspan="3" class="detail">
                    ${contract.ctotalMoney}
                </td>
            </tr>
            <tr>
                <td class="label">付款方式</td>
                <td colspan="3" class="detail">
                    ${contract.cpayType == 1 ? '月付':'年付'}
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
