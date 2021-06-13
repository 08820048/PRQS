<%--
  Created by IntelliJ IDEA.
  User: 22170
  Date: 2021/5/27
  Time: 22:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>管理员登录</title>
    <link rel="stylesheet" href="./css1/find_password.css">
</head>
<body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<!--表单验证开始：密码找回表单-->
<script>
    $(document).ready(function() {
        $(".find_btn").click(function(){
            /*验证用户名*/
            let u=document.getElementById("find_tel");
            if (u.validity.valueMissing === true) {
                u.setCustomValidity("用户名不能为空");
            } else if (u.validity.patternMismatch === true) {
                u.setCustomValidity("用户名必须由4~15位的中或英文字母组成");
            } else {
                u.setCustomValidity("");
            }
            /*验证新密码*/
            let pwd=document.getElementById("find_ident_code");
            if(pwd.validity.valueMissing===true){
                pwd.setCustomValidity("密码不能为空");
            }
            else if(pwd.validity.patternMismatch===true){
                pwd.setCustomValidity("密码必须由6~16位的英文字母或数字组成");
            }
            else{
                pwd.setCustomValidity("");
            }
        })
    })
</script>
<!--表单验证结束-->
<div class="all_find">
    <span>${adminlogin_msg}</span>
    <form action="${pageContext.request.contextPath}/AdminLoginServlet" method="post" name="find_password">
        <h1>管理员登录</h1>
        <label for="find_tel">
            <input type="text" id="find_tel"  name="username" placeholder="管理员账号" required="ssss"  pattern="[\u4e00-\u9fa5_a-zA-Z]{2,15}">
        </label>
        <label for="find_ident_code">
            <input type="text" id="find_ident_code" name="password" placeholder="管理员密码" required pattern="[a-zA-Z0-9]{6,16}">
        </label>
        <button type="submit" class="find_btn">确定</button>
    </form>
</div>
<!--   footer部分-->

</body>
</html>