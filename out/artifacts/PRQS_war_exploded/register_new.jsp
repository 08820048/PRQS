<%--
  Created by IntelliJ IDEA.
  User: 22170
  Date: 2021/5/29
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en"><head>

    <meta charset="UTF-8">
    <title>注册页</title>
    <link href="./css1/register_new.css" rel="stylesheet">
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
<!--表单验证开始：注册表单-->
<script>
    $(document).ready(function() {
        $(".sub_register").click(function(){
            /*验证用户名*/
            let u=document.getElementById("register_username");
            if(u.validity.valueMissing===true){
                u.setCustomValidity("用户名不能为空");
            }
            else if(u.validity.patternMismatch===true){
                u.setCustomValidity("用户名必须由4~15位的中或英文字母组成");
            }
            else{
                u.setCustomValidity("");
            }
            /*验证手机号*/
            let mobile=document.getElementById("register_tel");
            if(mobile.validity.patternMismatch===true){
                mobile.setCustomValidity("手机必须是1开头的11位数字");
            }
            else{
                mobile.setCustomValidity("");
            }
            /*验证邮箱*/
            let email=document.getElementById("register_email");
            if(email.validity.valueMissing===true){
                email.setCustomValidity("邮箱不能为空");
            }
            else if(email.validity.typeMismatch===true){
                email.setCustomValidity("邮箱格式不正确");
            }
            else{
                email.setCustomValidity("");
            }

            /*验证密码*/
            let pwd=document.getElementById("register_pwd");
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

<script>
    // let ls = document.querySelector('.tel_ipt');
    // let lp = document.querySelector('.tel_p');
    // ls.onblur = function () {
    //     if (ls.value.length < 11 || ls.value.length > 11) {
    //         lp.className = 'wrong';
    //         lp.innerHTML = '请输入11位数字'
    //     }
    // }
</script>
<script>
    // let lsp = document.querySelector('.pwd_ipt');
    // let lpp = document.querySelector('.pwd_p');
    // ls.onblur = function () {
    //     if (lsp.value.length < 6 || lsp.value.length > 16) {
    //         lpp.className = 'wrong';
    //         lpp.innerHTML = '请输入6-16个字符'
    //     }
    // }
</script>

<!--头部开始-->
<div class="box ">
    <header>
        <ul>
            <li index="1"><a href="#">注册</a></li>
        </ul>
    </header>
    <!--     头部结束-->
    <!--     内容部分开始-->
    <div class="content">
        <!--  注册部分开始-->
        <div class="register_content items" >
            <span><a href="login.jsp">已有帐号</a></span>
            <form name="login" action="${pageContext.request.contextPath}/RegisterUserServlet" method="post">
                <span>${register_msg}</span>
                <div class="eam_tel trs">
                    <label for="register_username">用户名</label>
                    <input name="username" class="tel_ipt" id="register_username" required="" type="text" placeholder="4~15位的中或英文字母组成" pattern="[\u4e00-\u9fa5_a-zA-Z]{2,15}">
                </div>
                <div class="eam_tel trs">
                    <label for="register_tel">手机号码</label>
                    <input name="telephone" class="tel_ipt" id="register_tel" required="" type="text" placeholder="请输入您的手机号" pattern="1\d{10}">
                </div>
                <div class="eam_tel trs">
                    <label for="register_email">邮箱</label>
                    <input name="email" class="tel_ipt" id="register_email" required="" type="email" placeholder="请输入Email" pattern="^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$">
                </div>

                <div class="trs">
                    <label for="register_pwd">密码</label>
                    <input name="password" class="tel_ipt" id="register_pwd" required="" type="text" placeholder="请输入6-16个字符的密码" pattern="[a-zA-Z0-9]{6,16}">
                    <p class="pwd_p"></p>
                </div>
                <div class="trs">
                    <label for="ident_code">验证码</label>
                    <input name="check" class="pwd_ipt" id="ident_code" type="text" placeholder="请输入验证码">
                    <a href="javascript:refreshCode();">
                        <img class="verifyImg" id="vcode" onclick="javascript:this.src='xxx'+Math.random();" alt="" src="${pageContext.request.contextPath}/CheckCodeServlet">
                    </a>

                    <p class="pwd_p"></p>
                </div>
                <button class="sub_register" type="submit">立即注册</button>
            </form>
        </div>
    </div>
    <script type="text/javascript">
        //验证码切换的函数
        function refreshCode(){
            //1.获取验证码图片对象
            var vcode = document.getElementById("vcode");
            //2.设置src属性，加时间戳
            vcode.src="${pageContext.request.contextPath}/CheckCodeServlet?time="+new Date().getTime();
        }
    </script>
    <!--     注册部分结束-->
</div>
<!--     内容部分结束-->
</body></html>
