<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>XX系统 - 房屋信息添加</title>
    <link rel="stylesheet" href="/css/main.css">
    <link rel="stylesheet" href="/lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="box">
    <h3>添加房屋信息</h3>
    <form action="/house/add" method="post">
        <table class="form-table">
            <tr>
                <td>地区</td>
                <td colspan="3" class="control">
                    <input type="text" name="haddress" placeholder="填写地区">
                </td>
            </tr>
            <tr>
                <td>小区名字</td>
                <td colspan="3" class="control">
                    <input type="text" name="" placeholder="小区名字">
                </td>
            </tr>
            <tr>
                <td>单元号</td>
                <td colspan="3" class="control">
                    <input type="text" name="" placeholder="单元号">
                </td>
            </tr>
            <tr>
                <td>楼层</td>
                <td colspan="3" class="control">
                    <input type="text" name="hfloor" placeholder="楼层">
                </td>
            </tr>
            <tr>
                <td>房间号</td>
                <td colspan="3" class="control">
                    <input type="text" name="hroomNum" placeholder="房间号">
                </td>
            </tr>
            <tr>
                <td>面积（平米）</td>
                <td colspan="3" class="control">
                    <input type="text" name="harea" placeholder="面积">
                </td>
            </tr>
            <tr>
                <td>朝向</td>
                <td colspan="3" class="control">
                    <input type="text" name="hdir" placeholder="朝向">
                </td>
            </tr>
            <tr>
                <td>装修</td>
                <td colspan="3" class="control">
                    <input type="text" name="hdeco" placeholder="装修">
                </td>
            </tr>
            <tr>
                <td>是否双气</td>
                <td colspan="3" class="control">
                    <input type="text" name="hair" placeholder="是否双气">
                </td>
            </tr>
            <tr>
                <td>限住人数</td>
                <td colspan="3" class="control">
                    <input type="text" name="" placeholder="限住人数">
                </td>
            </tr>
            <tr>
                <td>配套设施</td>
                <td colspan="3" class="control">
                    <input type="text" name="" placeholder="配套设施">
                </td>
            </tr>
            <tr>
                <td>出租价格（元/月）</td>
                <td colspan="3" class="control">
                    <input type="text" name="hprice" placeholder="出租价格">
                </td>
            </tr>
            <tr>
                <td>出租状态</td>
                <td colspan="3" class="control">
                    <select name="hrentStatus">
                        <option>出租状态</option>
                        <option value="1">已出租</option>
                        <option value="2">未出租</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>房屋图片</td>
                <td colspan="3" class="control">
                    <input type="file" name="" placeholder="">
                </td>
            </tr>
            <tr>
                <td>完整地址信息</td>
                <td colspan="3" class="control">
                    <input type="text" name="" placeholder="完整地址信息">
                </td>
            </tr>

            <tr>
                <td>备注说明</td>
                <td colspan="3" class="control">
                    <textarea class="p100" name="" placeholder="备注说明信息"></textarea>
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
