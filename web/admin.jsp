<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>XX系统 - 后台管理</title>
    <link rel="stylesheet" href="../../css/admin.css">
    <link rel="stylesheet" href="../../lib/font-awesome/css/font-awesome.css">
</head>
<body>
<div class="header">
    <h1><a href="#">XX系统</a></h1>
    <!--<div class="nav">
        <a target="right" href="#">工作台</a>
        <a target="right" href="#">报表</a>
        <a target="right" href="#">客户信息</a>
        <a target="right" href="#">员工信息</a>
        <a target="right" href="#">公告信息</a>
        <a target="right" href="#">发件箱</a>
    </div>-->
    <div class="user">
        <a href="#">XX用户</a>
        <a href="login.html">退出</a>
    </div>
</div>
<div class="main">
    <ul class="left-side">
        <li class="menu-title active">
            <a href="#">
                <i class=" fa fa-users"></i>&nbsp;&nbsp;公寓管理
            </a>
        </li>
        <li class="sub-menu">
            <ul>
                <li class="active">
                    <a href="/house/list" target="pageBox">
                        <i class=" fa fa-circle-thin"></i>&nbsp;&nbsp;房屋信息
                    </a>
                </li>
                <li>
                    <a href="/contract/list" target="pageBox">
                        <i class=" fa fa-circle-thin"></i>&nbsp;&nbsp;租赁合同
                    </a>
                </li>
                <li>
                    <a href="/lessee/list" target="pageBox">
                        <i class=" fa fa-circle-thin"></i>&nbsp;&nbsp;租户信息
                    </a>
                </li>
                <li>
                    <a href="/rent/list" target="pageBox">
                        <i class=" fa fa-circle-thin"></i>&nbsp;&nbsp;房租信息
                    </a>
                </li>

            </ul>
        </li>
        <li class="menu-title">
            <a href="#">
                <i class=" fa fa-file-text"></i>&nbsp;&nbsp;服务管理
            </a>
        </li>
        <li class="sub-menu">
            <ul>
                <li>
                    <a target="pageBox" href="">
                        <i class=" fa fa-circle-thin"></i>&nbsp;&nbsp;后勤人员
                    </a>
                </li>
                <li>
                    <a target="pageBox" href="">
                        <i class=" fa fa-circle-thin"></i>&nbsp;&nbsp;修缮记录
                    </a>
                </li>
                <li>
                    <a target="pageBox" href="">
                        <i class=" fa fa-circle-thin"></i>&nbsp;&nbsp;公寓活动
                    </a>
                </li>

            </ul>
        </li>
        <li class="menu-title">
            <a href="#">
                <i class=" fa fa-sitemap"></i>&nbsp;&nbsp;管理员
            </a>
        </li>
        <li class="sub-menu">
            <ul class="menu">
                <li>
                    <a target="pageBox" href="#">
                        <i class=" fa fa-circle-thin"></i>&nbsp;&nbsp;角色管理
                    </a>
                </li>
                <li>
                    <a target="pageBox" href="/admin/list">
                        <i class=" fa fa-circle-thin"></i>&nbsp;&nbsp;用户管理
                    </a>
                </li>
            </ul>
        </li>
    </ul>
    <div class="right-side">
        <iframe name="pageBox" src="/house/list" ></iframe>
        <div class="footer">
            智游教育 ©2018 河南智游臻龙教育科技有限公司
        </div>
    </div>
</div>
<script src="/lib/jquery/jquery.js"></script>
<script src="/js/admin.js"></script>
</body>
</html>
