<%--
  Created by IntelliJ IDEA.
  User: 22170
  Date: 2021/5/29
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="./css1/login.css">
</head>
<body>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<!--表单验证开始：登录表单-->
<script type="text/javascript">
    $(document).ready(function() {
        $(".submit_btn").click(function () {
            /*验证用户名*/
            let u = document.getElementById("login_style");
            if (u.validity.valueMissing === true) {
                u.setCustomValidity("用户名不能为空");
            } else if (u.validity.patternMismatch === true) {
                u.setCustomValidity("用户名必须由4~15位的中或英文字母组成");
            } else {
                u.setCustomValidity("");
            }
            /*验证密码*/
            let pwd = document.getElementById("login_pwd");
            if (pwd.validity.valueMissing === true) {
                pwd.setCustomValidity("密码不能为空");

            } else if (pwd.validity.patternMismatch === true) {
                pwd.setCustomValidity("密码必须由6~16位的英文字母或数字组成");
            } else {
                pwd.setCustomValidity("");
            }
        })
    });
</script>
<!--表单验证结束-->
<div class="box ">
    <header>
        <ul>
            <li class="current" index="0"><a href="#">登录</a></li>
        </ul>
    </header>
    <!--     头部结束-->
    <!--     内容部分开始-->
    <div class="content">
        <!--         登录部分开始-->
        <div class="login_content items" style="display: block">
            <span style="margin-left: 300px"><a href="register_new.jsp">没有账号?</a></span>
            <span>${login_msg}</span>
            <form name="login" action="${pageContext.request.contextPath}/loginUserServlet" method="post">
                <div class="eam_tel trs">
                    <label for="login_style"><span>用户名</span></label>
                    <input name="username" class="tel_ipt" id="login_style"  type="text" placeholder="4~15位的中或英文字母组成" required="required"  pattern="[\u4e00-\u9fa5_a-zA-Z]{2,15}">
                    <p class="tel_p"></p>
                </div>

                <div class="trs">
                    <label for="login_pwd">密码</label>
                    <input name="password" class="tel_ipt" id="login_pwd" required="" type="text" placeholder="请输入6-16个字符的密码" pattern="[a-zA-Z0-9]{6,16}">
                    <p class="pwd_p"></p>
                </div>

                <div class="trs">
                    <label for="code">验证码</label>
                    <input name="vcode_l" class="pwd_ipt" id="code" type="text" placeholder="点击可刷新">
                    <a href="javascript:refreshCode_login();">
                        <img class="verifyImg" id="vcode_l" onclick="javascript:this.src='xxx'+Math.random();" alt="" src="${pageContext.request.contextPath}/CheckCodeServlet">
                    </a>
                    <p class="pwd_p"></p>
                </div>
                <div class="trs forget_pwd">
                    <input name="auto_login" class="forget_ipt" id="login_auto" type="checkbox" value="0">下次自动登录
                </div>
                <div class="trs forget_pwd">
                    <a href="findPwd.jsp">忘记密码？</a>
                    <a href="AdminLogin.jsp">管理员登录？</a>
                </div>
                <button class="submit_btn" type="submit">立即登录</button>
            </form>
        </div>
    </div>
</div>
<!--         登录部分结束-->
<script type="text/javascript">

    //验证码切换的函数
    function refreshCode_login(){
        //1.获取验证码图片对象
        var vcode = document.getElementById("vcode_l");
        //2.设置src属性，加时间戳
        vcode.src="${pageContext.request.contextPath}/CheckCodeServlet?time="+new Date().getTime();
    }
</script>
</body>
</html>
