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
        function deleteArticle(ins_id){
            //用户安全提示
            if(confirm("该操作不可撤销,您确定要删除吗？")){
                //访问路径
                location.href="${pageContext.request.contextPath}/DelInsServlet?ins_id="+ins_id;
            }
        }
        window.onload = function(){
            //给删除选中按钮添加单击事件
            document.getElementById("delSelected").onclick = function(){
                if(confirm("该操作不可撤销，您确定要删除所选吗？")){
                    let flag = false;
                    //判断是否有选中条目
                    let selected = document.getElementsByName("ins_id");
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
                let cbs = document.getElementsByName("ins_id");
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
                            href="InsAdd.jsp">新增</a></button>
                    <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span><a id="delSelected" href="javascript:void(0)">批量删除</a></button>
                </div>
            </div>
        </div>
        <div class="am-u-sm-12 am-u-md-3">

        </div>
        <div class="am-u-sm-12 am-u-md-3">
            <div class="am-input-group am-input-group-sm">
                <form action="${pageContext.request.contextPath}/AdminBaseServlet" method="POST" enctype="application/x-www-form-urlencoded">
                <input type="text" class="am-form-field" placeholder="请输入机构名称..." name="ins_name" value="${condition.ins_name[0]}"/>
                <span class="am-input-group-btn">
                <input class="am-btn am-btn-default" value="搜索" type="submit"/>
                </span>
            </form>
        </div>
    </div>
    <div class="am-g">
        <div class="am-u-sm-12">
            <form class="am-form" id="form" action="${pageContext.request.contextPath}/DelInsServlet" method="post">
                <table class="am-table am-table-striped am-table-hover table-main">
                    <thead>
                    <tr>
                        <th class="table-check"><input type="checkbox" id="first"></th>
                        <th class="table-id">ID</th>
                        <th class="table-title">标题</th>
                        <th class="table-type">推荐指数</th>
                        <th class="table-author am-hide-sm-only">机构概述</th>
                        <th class="table-date am-hide-sm-only">修改日期</th>
                        <th class="table-set">操作</th>
                    </tr>
                    </thead>
                    <tbody>
            <c:forEach items="${pb.list}" var="Ins" varStatus="index">
                    <tr>
                        <td><input type="checkbox" name="ins_id" value="${Ins.ins_id}"></td>
                        <td>${index.count}</td>
                        <td>
                            <a href="#">${Ins.ins_name}</a>
                        </td>
                        <td>${Ins.ins_recommend}</td>
<%--                            <input type="text" name="ins_description" style="border: none" disabled="disabled" value="${Ins.ins_description}" />--%>
<%--                            <textarea name="ins_description" id="ins_description" cols="30" rows="10" disabled="disabled"> ${Ins.ins_description}</textarea>--%>
                        <td>
                        <p>${Ins.ins_description}</p>
                        </td>
                        <td class="am-hide-sm-only">${Ins.ins_date}</td>
                        <td>
                            <div class="am-btn-toolbar">
                                <div class="am-btn-group am-btn-group-xs">
                                    <button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span><a href="${pageContext.request.contextPath}/FindInsServlet?ins_id=${Ins.ins_id}">编辑</a></button>
                                    <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> <a href="javascript:deleteArticle(${Ins.ins_id});">删除</a></button>
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
                                <a href="${pageContext.request.contextPath}/AdminBaseServlet?currentPage=${pb.currentPage-1}&rows=5&ins_name=${condition.ins_name[0]}">&laquo;</a>
                            </li>
                            </c:if>


                            <c:if test="${pb.currentPage!=1}">

                                <li class="am-active">
                                    <a href="${pageContext.request.contextPath}/AdminBaseServlet?currentPage=${pb.currentPage-1}&rows=5&ins_name=${condition.ins_name[0]}">&laquo;</a>
                                </li>
                            </c:if>

                            <c:if test="${pd.currentPage+1==totalPage}">

                                <li class="am-disabled">
                                    <a href="${pageContext.request.contextPath}/AdminBaseServlet?currentPage=${pb.currentPage-1}&rows=5&ins_name=${condition.ins_name[0]}">&laquo;</a>
                                </li>
                            </c:if>

                        <c:forEach begin="1" end="${pb.totalPage}" var="i">
                            <c:if test="${pb.currentPage==i}">
                            <li class="am-disabled">
                                <a href="${pageContext.request.contextPath}/AdminBaseServlet?currentPage=${i}&rows=5&ins_name=${condition.ins_name[0]}">${i}</a>
                            </li>
                            </c:if>

                            <c:if test="${pb.currentPage!=i}">
                                <li class="am-active">
                                    <a href="${pageContext.request.contextPath}/AdminBaseServlet?currentPage=${i}&rows=5">${i}</a>
                                </li>
                            </c:if>
                        </c:forEach>

                            <li>
                                <a href="${pageContext.request.contextPath}/AdminBaseServlet?currentPage=${pb.currentPage+1}&rows=5&ins_name=${condition.ins_name[0]}">&raquo;</a>
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


<!--<!DOCTYPE html>-->
<!--<html>-->
<!--	<head>-->
<!--		<meta charset="UTF-8">-->
<!--		<title></title>-->
<!--		<link rel="stylesheet" href="css/amazeui.min.css" />-->
<!--		 <link rel="stylesheet" href="css/admin.css" />-->
<!--	</head>-->
<!--	<body>-->
<!--		<div class="admin-content-body">-->
<!--      <div class="am-cf am-padding am-padding-bottom-0">-->
<!--        <div class="am-fl am-cf"><strong class="am-text-primary am-text-lg">用户留言</strong><small></small></div>-->
<!--      </div>-->

<!--      <hr>-->

<!--      <div class="am-g">-->
<!--        <div class="am-u-sm-12 am-u-md-6">-->
<!--          <div class="am-btn-toolbar">-->
<!--            <div class="am-btn-group am-btn-group-xs">-->
<!--            </div>-->
<!--          </div>-->
<!--        </div>-->
<!--        <div class="am-u-sm-12 am-u-md-3">-->
<!--    -->
<!--        </div>-->
<!--        <div class="am-u-sm-12 am-u-md-3">-->
<!--          <div class="am-input-group am-input-group-sm">-->
<!--            <input type="text" class="am-form-field">-->
<!--          <span class="am-input-group-btn">-->
<!--            <button class="am-btn am-btn-default" type="button">搜索</button>-->
<!--          </span>-->
<!--          </div>-->
<!--        </div>-->
<!--      </div>-->
<!--      <div class="am-g">-->
<!--        <div class="am-u-sm-12">-->
<!--          <form class="am-form">-->
<!--            <table class="am-table am-table-striped am-table-hover table-main">-->
<!--              <thead>-->
<!--              <tr>-->
<!--                <th class="table-check"><input type="checkbox"></th><th class="table-id">ID</th><th class="table-title">标题</th><th class="table-type">内容</th><th class="table-author am-hide-sm-only">留言人</th><th class="table-date am-hide-sm-only">日期</th><th class="table-set">操作</th>-->
<!--              </tr>-->
<!--              </thead>-->
<!--              <tbody>-->
<!--              <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--              -->
<!--               <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--              -->
<!--              -->
<!--               <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--               <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--              -->
<!--              -->
<!--               <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--              -->
<!--              -->
<!--               <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--              -->
<!--              -->
<!--               <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--              -->
<!--              -->
<!--               <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--              -->
<!--              -->
<!--               <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--              -->
<!--              -->
<!--               <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--              -->
<!--              -->
<!--               <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--              -->
<!--              -->
<!--               <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--              -->
<!--              -->
<!--               <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--              -->
<!--              -->
<!--               <tr>-->
<!--                <td><input type="checkbox"></td>-->
<!--                <td>1</td>-->
<!--                <td><a href="#">这个模板很不错</a></td>-->
<!--                <td>真的很不错哎</td>-->
<!--                <td class="am-hide-sm-only">管理员</td>-->
<!--                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>-->
<!--                <td>-->
<!--                  <div class="am-btn-toolbar">-->
<!--                    <div class="am-btn-group am-btn-group-xs">-->
<!--                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-hide-sm-only"><span class="am-icon-trash-o"></span> 删除</button>-->
<!--                    </div>-->
<!--                  </div>-->
<!--                </td>-->
<!--              </tr>-->
<!--           -->
<!--              </tbody>-->
<!--            </table>-->
<!--            <div class="am-cf">-->
<!--              共 15 条记录-->
<!--              <div class="am-fr">-->
<!--                <ul class="am-pagination">-->
<!--                  <li class="am-disabled"><a href="#">?</a></li>-->
<!--                  <li class="am-active"><a href="#">1</a></li>-->
<!--                  <li><a href="#">2</a></li>-->
<!--                  <li><a href="#">3</a></li>-->
<!--                  <li><a href="#">4</a></li>-->
<!--                  <li><a href="#">5</a></li>-->
<!--                  <li><a href="#">?</a></li>-->
<!--                </ul>-->
<!--              </div>-->
<!--            </div>-->
<!--            <hr>-->
<!--          </form>-->
<!--        </div>-->

<!--      </div>-->
<!--    </div>-->
<!--	</body>-->
<!--</html>-->
