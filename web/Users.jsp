<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="./admin-2/css/amazeui.min.css" />
    <link rel="stylesheet" href="./admin-2/css/admin.css" />
    <script>
        function deleteUser(id){
            //用户安全提示
            if(confirm("该操作不可撤销,您确定要删除吗？")){
                //访问路径
                location.href="${pageContext.request.contextPath}/DelUserServlet?id="+id;
            }
        }
        window.onload = function(){
            //给删除选中按钮添加单击事件
            document.getElementById("delSelected").onclick = function(){
                if(confirm("该操作不可撤销，您确定要删除所选吗？")){
                    let flag = false;
                    //判断是否有选中条目
                    let selected = document.getElementsByName("id");
                    for (let i = 0; i < selected.length; i++) {
                        if(selected[i].checked){
                            //有一个条目选中了
                            flag = true;
                            break;
                        }
                    }
                    if(flag){//有条目被选中
                        //表单提交
                        document.getElementById("form").submit();
                    }
                }
            }
            //1.获取第一个checkbox
            document.getElementById("first").onclick = function(){
                //2.获取下边列表中所有的cb
                let cbs = document.getElementsByName("id");
                //3.遍历
                for (let i = 0; i < cbs.length; i++) {
                    //4.设置这些cbs[i]的checked状态 = firstCb.checked
                    cbs[i].checked = this.checked;
                }
            }
        }
    </script>
</head>

<body>
<div class="admin-content-body">
    <div class="am-cf am-padding am-padding-bottom-0">
        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">培训机构管理</strong><small></small></div>
    </div>

    <hr>

    <div class="am-g">
        <div class="am-u-sm-12 am-u-md-6">
            <div class="am-btn-toolbar">
                <div class="am-btn-group am-btn-group-xs">
                    <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span><a
                            href="${pageContext.request.contextPath}/UsernameServlet">添加管理员</a></button>
                    <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span><a id="delSelected" href="javascript:void(0)">批量删除</a></button>
                </div>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">

        </div>
        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <form action="${pageContext.request.contextPath}/UsersListServlet" method="POST" enctype="application/x-www-form-urlencoded">
                <input type="text" class="am-form-field" placeholder="请输入用户名..." name="username" value="${condition.username[0]}"/>
                <span class="am-input-group-btn">
                <input class="am-btn am-btn-default" value="搜索" type="submit"/>
                </span>
            </form>
        </div>
    </div>
    <div class="am-g">
        <div class="am-u-sm-12">
            <form class="am-form" id="form" action="${pageContext.request.contextPath}/DelUserServlet" method="post">
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th class="table-check"><input type="checkbox" id="first"></th>
                        <th class="table-id">编号</th>
                        <th class="table-title">用户名</th>
                        <th class="table-type">电话</th>
                        <th class="table-type">邮箱</th>
                       <th class="table-title">密码</th>
                        <th></th>
                        <th class="table-type">权限</th>
                        <th></th>
                        <th class="table-type">是否激活</th>
                        <th></th>
                        <th class="table-set">操作</th>
                    </tr>
                    </thead>
                    <tbody>
            <c:forEach items="${pb.list}" var="user" varStatus="index">
                    <tr>
                        <td><input type="checkbox" name="id" value="${user.id}"></td>
                        <td>${index.count}</td>
                        <td>
                            <a href="#">${user.username}</a>
                        </td>
                        <td>${user.telephone}</td>
                        <td>
                        <p>${user.email}</p>
                        </td>
                        <td class="am-hide-sm-only" style="color: black">${user.password}</td>
                        <td>
                        <td>
                            <c:if test="${user.mid=='N'}">
                            <p style="color: gray">普通用户</p>
                            </c:if>
                            <c:if test="${user.mid=='Y'}">
                                <p style="color: #0a628f">管理员</p>
                            </c:if>
                        </td>
                        <th></th>
                        <td>
                           <c:if test="${user.status=='N'}">
                                <p style="color: red">未激活</p>
                           </c:if>
                            <c:if test="${user.status=='Y'}">
                                <p style="color: cornflowerblue">已激活</p>
                            </c:if>
                        </td>
                        <th></th>
                        <td>
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <button disabled="disabled" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span><a href="javascript:void(0)">编辑</a></button>
                                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> <a href="javascript:deleteUser(${user.id});">删除</a></button>
                                </div>
                            </div>
                        </td>
                    </tr>
            </c:forEach>
                    </tbody>
                </table>
                <div class="am-cf">
                    共 ${pb.totalCount} 条记录,共 ${pb.totalPage} 页
                    <div class="am-fr">
                        <ul class="am-pagination">
                            <c:if test="${pb.currentPage==1}">

                            <li class="am-disabled">
                                <a href="${pageContext.request.contextPath}/UsersListServlet?currentPage=${pb.currentPage-1}&rows=5&username=${condition.username[0]}">&laquo;</a>
                            </li>
                            </c:if>
                            <c:if test="${pb.currentPage!=1}">

                                <li class="am-active">
                                    <a href="${pageContext.request.contextPath}/UsersListServlet?currentPage=${pb.currentPage-1}&rows=5&username=${condition.username[0]}">&laquo;</a>
                                </li>
                            </c:if>

                            <c:if test="${pb.currentPage+1==totalPage}">

                                <li class="am-disabled">
                                    <a href="${pageContext.request.contextPath}/UsersListServlet?currentPage=${pb.currentPage-1}&rows=5&username=${condition.username[0]}">&laquo;</a>
                                </li>
                            </c:if>

                        <c:forEach begin="1" end="${pb.totalPage}" var="i">
                            <c:if test="${pb.currentPage==i}">
                            <li class="am-disabled">
                                <a href="${pageContext.request.contextPath}/UsersListServlet?currentPage=${i}&rows=5&username=${condition.username[0]}">${i}</a>
                            </li>
                            </c:if>

                            <c:if test="${pb.currentPage!=i}">
                                <li class="am-active">
                                    <a href="${pageContext.request.contextPath}/UsersListServlet?currentPage=${i}&rows=5">${i}</a>
                                </li>
                            </c:if>
                        </c:forEach>
                            <li>
                                <a href="${pageContext.request.contextPath}/UsersListServlet?currentPage=${pb.currentPage+1}&rows=5&username=${condition.username[0]}">&raquo;</a>
                            </li>

                        </ul>
                    </div>
                </div>
                <hr>
            </form>
        </div>
    </div>
    </div>
</div>
</body>
</html>
