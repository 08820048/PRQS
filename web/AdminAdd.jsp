<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="admin/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="admin/css/main.css"/>
    <script type="text/javascript" src="admin/js/libs/modernizr.min.js"></script>
</head>
<body>
<div class="container clearfix">
    <!--/sidebar-->
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="AdminIndex.html">管理首页</a><span class="crumb-step">&gt;</span><a class="crumb-name" href="Institution.jsp">用户管理</a><span class="crumb-step">&gt;</span><span>添加管理员</span></div>
        </div>
        <div class="result-wrap">
            <div class="result-content">
                <form action="${pageContext.request.contextPath}/AddAdminServlet" method="post" id="myform"  name="myform" >
                    <table class="insert-tab" width="100%">
                        <tr>
                            <td><p>请选择要添加的用户:</p></td>
                        </tr>
                        <tr>
                            <th>用户列表:</th>
                            <td>
                                <select name="username" id="">
                                    <c:forEach items="${users}" var="user">
                                        <c:if test="${user.mid=='N'}">
                                            <option value="${user.username}">${user.username}</option>
                                        </c:if>
                                    </c:forEach>

<%--                                    <option value="普通推荐" selected>普通推荐</option>--%>
<%--                                    <option value="史诗推荐">史诗推荐</option>--%>
                                </select>
                            </td>
                        </tr>



                        <tr>
                            <th></th>
                            <td>
                                <input class="btn btn-primary btn6 mr10" value="提交" type="submit">
                                <input class="btn btn6" onclick="history.go(-1)" value="返回" type="button">
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>