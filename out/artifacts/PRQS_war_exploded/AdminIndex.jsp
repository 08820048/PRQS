<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 22170
  Date: 2021/5/23
  Time: 14:03
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png" />
    <link rel="icon" type="image/png" href="../assets/img/favicon.png" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

    <title>Material Dashboard by Creative Tim</title>

    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />

    <!-- Bootstrap core CSS     -->
    <link href="./admin-2/css/bootstrap.min.css" rel="stylesheet" />

    <!--  Material Dashboard CSS    -->
    <link href="./admin-2/css/material-dashboard.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="./admin-2/css/demo.css" rel="stylesheet" />

    <!--     Fonts and icons     -->
    <link href="./admin-2/css/font-awesome.min.css" rel="stylesheet">
    <link href='./admin-2/css/2d7207a20f294df196f3a53cae8a0def.css' rel='stylesheet' type='text/css'>
</head>
<body>
<div class="wrapper">

    <div class="sidebar" data-color="purple" data-image="../assets/img/sidebar-1.jpg">

        <div class="logo">
            <a href="#" class="simple-text">
                编程资源网-网站管理后台
            </a>
            <a href="${pageContext.request.contextPath}/ShowBaseServlet?status=selectAllByIns">回首页</a>
        </div>

        <div class="sidebar-wrapper">
            <ul class="nav">
                <li>
                    <a href="${pageContext.request.contextPath}/AdminBaseServlet" target="right" >
                        <i class="material-icons">person</i>
                        <p>培训机构管理</p>
                    </a>
                </li>
                <li>
                    <a  href="${pageContext.request.contextPath}/BooksListServlet" target="right" >
                        <i class="material-icons">library_books</i>
                        <p>推荐书籍管理</p>
                    </a>
                </li>
                <li>
                    <a href="soft.jsp" target="right" >
                        <i class="material-icons">dashboard</i>
                        <p>推荐软件管理</p>
                    </a>
                </li>
                <li>
                    <a href="soft.jsp" target="right" >
                        <i class="material-icons">content_paste</i>
                        <p>语言栏目管理</p>
                    </a>
                </li>

                <li>
                    <a href="${pageContext.request.contextPath}/UsersListServlet" target="right" >
                        <i class="material-icons">person</i>
                        <p>用户管理</p>
                    </a>
                </li>
<%--                <li>--%>
<%--                    <a href="javascript:void(0)" onclick="updatePwd('修改密码',5)">--%>
<%--                        <i class="material-icons">location_on</i>--%>
<%--                        <p disabled="">密码修改</p>--%>
<%--                    </a>--%>
<%--                </li>--%>
                <li>
                    <a href="error.jsp" target="right" >
                        <i class="material-icons">person</i>
                        <p>404错误页面</p>
                    </a>
                </li>

            </ul>
        </div>
    </div>

    <div class="main-panel">
        <nav class="navbar navbar-transparent navbar-absolute" style="background-color: #fff;">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                        <li>
                            <a href="#pablo" class="dropdown-toggle" data-toggle="dropdown">
<%--                                <i class="material-icons">dashboard</i>--%>
                    <c:if test="${not empty sessionScope.user}">
                                <span>
                            ${sessionScope.user}
                              </span>
                        </c:if>
                     <c:if test="${empty sessionScope.user}">
                                <span>


                                </span>
                     </c:if>
                                <p class="hidden-lg hidden-md">Dashboard</p>
                            </a>
                        </li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="material-icons">person</i>
                                <p class="hidden-lg hidden-md">搜索</p>
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="${pageContext.request.contextPath}/LoginOutServlet">退出</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="content">
            <div class="container-fluid">
                <div class="row" style="margin-top: -15px;">
                    <iframe src="${pageContext.request.contextPath}/AdminBaseServlet" width="100%" height="900" name="right" style="border: none;"></iframe>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<!--   Core JS Files   -->
<script src="./admin-2/js/jquery-3.1.0.min.js" type="text/javascript"></script>
<script src="./admin-2/js/bootstrap.min.js" type="text/javascript"></script>
<script src="./admin-2/js/material.min.js" type="text/javascript"></script>

<!--  Charts Plugin -->
<script src="./admin-2/js/chartist.min.js"></script>



<script src="./admin-2/js/material-dashboard.js"></script>

<!-- Material Dashboard DEMO methods, don't include it in your project! -->

<script type="text/javascript" src="./admin-2/myplugs/js/plugs.js">
</script>
<script type="text/javascript">
    //添加编辑弹出层
    function updatePwd(title, id) {
        $.jq_Panel({
            title: title,
            iframeWidth: 500,
            iframeHeight: 300,
            url: "updatePwd.html"
        });
    }
</script>


<script type="text/javascript">
    $(document).ready(function(){

        $(".nav li").click(function(){

            $(".nav li").removeClass("active");
            $(this).addClass("active");

        })

        // Javascript method's body can be found in assets/js/demos.js

    });
</script>

</html>

