<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: 22170
  Date: 2021/5/21
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!--    <link rel="stylesheet" href="../js/swiper-bundle.min.css">-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>编程资源查询系统-推荐编程培训机构、书籍以及相关软件</title>
    <meta name="description" content="编程资源查询网站--以帮助初级编程小白选择心仪的培训机构、书籍、以及方便快捷的软件为主旨，为各位小白节省时间，提高效率">
    <meta name="keywords" content="编程培训机构、编程语言书籍、编程软件">
    <link rel="stylesheet" type="text/css" href="./bootstrap-4.4.1-dist/bootstrap-4.4.1-dist/css/bootstrap.css" />
    <link rel="stylesheet" href="./css/index.css">
    <link rel="stylesheet" href="./css/base.css">

</head>
<body>
<!--头部开始-->
<header class="header w">
    <!--       logo部分-->
    <div class="logo">
        <h1><a href="index.html" title="编程资源查询系统"><img src="./images/logo.png" alt="">编程资源查询系统</a></h1>
        <p>欢迎来到编程资源查询系统！</p>
    </div>
    <!--       搜索部分-->
    <div class="search">
        <label for="">
            <input type="search" name="search1" id="" placeholder="请输入关键字">
        </label>
        <button>搜索</button>
    </div>
    <!--       用户登录部分-->
    <c:if test="${not empty sessionScope.user}">
    <div class="user">
        <div class="user_name"><a href="#">欢迎你:</a></div>
<%--        <div class="user_img">--%>
<%--        </div>--%>
        <div class="login_style">
            ${sessionScope.user}
        </div>
        <button><a style="color: red" href="${pageContext.request.contextPath}/LoginOutServlet">注销</a></button>
    </div>
    </c:if>
</header>
<!--头部结束-->
<!--   导航栏部分开始-->
<nav class="nav">
    <ul>
        <li><a href="#">首页</a></li>
        <li><a href="#">培训机构</a></li>
        <li><a href="#">书籍</a></li>
        <li><a href="#">编程软件</a></li>
    </ul>
</nav>
<!--轮播图部分开始-->
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
    <ol class="carousel-indicators">
        <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
        <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <div class="carousel-item active">
            <img src="./images/swiper1.jpeg" class="d-block w-100" alt="..." height="400px">
        </div>
        <div class="carousel-item">
            <img src="./images/swiper3.jpeg" class="d-block w-100" alt="..." height="400px">
        </div>
        <div class="carousel-item">
            <img src="./images/swiper4.jpg" class="d-block w-100" alt="..." height="400px">
        </div>

    </div>
    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<!--轮播图部分结束-->
<!--机构推荐开始-->
<div class="training">
    <div class="items_hd w">
        <h4 class="recom_name">培训机构</h4>
        <div class="more"><a href="#">更多</a></div>
    </div>

    <div class="train_bd w">
        <ul>
            <c:forEach items="${institutions}" var="institutions" varStatus="index">
            <li>
                <a href="http://www.bjpowernode.com/">  <img src="./logo/logo-${index.count}.png" alt="">
                    <h5><em>机构名称：</em>${institutions.ins_name}</h5>
                    <h5>
                        <em> 推荐级别：</em>${institutions.ins_recommend}
                    </h5>
                    <p><em>开设课程：</em> ${institutions.ins_description}</p>
                </a>
            </li>
            </c:forEach>
        </ul>


<%--            <li>--%>
<%--                <a href="#">  <img src="./logo/logo-2.png" alt="">--%>
<%--                    <h5><em>机构名称：</em>千锋教育</h5>--%>
<%--                    <h5><em> 推荐级别：</em>史诗推荐</h5>--%>
<%--                    <p><em>开设课程：</em>  千锋教育面授课程包含HTML5 大前端培训、JavaEE+分布式开发培训、Python全栈+人工智能培训、全链路UI/UE设计培训、物联网+嵌入式培训、 360 网络安全、大数据+人工智能培训、全栈软件测试培训、PHP全栈+服务器集群培训、云计算+信息安全培训、Unity游戏开发培训、区块链.</p>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="#">  <img src="./logo/logo-3.png" alt="">--%>
<%--                    <h5><em>机构名称：</em>黑马程序员</h5>--%>
<%--                    <h5><em> 推荐级别：</em>精品推荐</h5>--%>
<%--                    <p><em>开设课程：</em> JavaEE、PHP+H5 全栈工程师、产品经理、前端与移动开发、C/C++与网络攻防、新媒体+短视频运营、电商运营、人工智能+Python、云计算大数据、UI/UE设计、视觉设计、软件测试、网络安全+运维工程师、Go语言与区块链、微信小程序开发、影视制作等培训学科。</p>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="#">  <img src="./logo/logo-4.png" alt="">--%>
<%--                    <h5><em>机构名称：</em>爱前端</h5>--%>
<%--                    <h5> <em>推荐级别：</em>史诗推荐</h5>--%>
<%--                    <p><em>开设课程：</em>html css教程、js基础入门、深入浅出的js面向对象、ajax课程、node.js课程、angular课程、vue课程、react课程、canvas游戏课程、炫酷css3 特效课程等等，希望帮助更多的 0 基础学员，让更多的学员爱上前端开发!</p>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="#">  <img src="./logo/logo-5.png" alt="">--%>
<%--                    <h5><em>机构名称：</em>火星时代</h5>--%>
<%--                    <h5> <em>推荐级别：</em>普通推荐</h5>--%>
<%--                    <p><em>开设课程：</em>游戏设计、互动媒体、影视动画、建筑等</p>--%>
<%--                </a>--%>
<%--            </li>--%>

    </div>
</div>
<!--培训部分结束-->
<!--书籍推荐部分-->
<div class="books">
    <div class="items_hd w">
        <h4 class="recom_name">书籍</h4>
        <div class="books_items">

            <ul>
<c:forEach items="${codeTypes}" var="codeTypes" varStatus="index">
                <li><a href="#">${codeTypes.code_name}</a>|</li>

</c:forEach>
    </ul>
        </div>
        <div class="more"><a href="#">更多</a></div>
    </div>
    <div class="books_bd w">

        <ul>
            <c:forEach items="${books}" var="books" varStatus="index">
            <li>
                <a href="#">  <img src="./book_imgs/book-${index.count}.png" alt="">
                    <h5>书籍名字：${books.books_name}</h5>
                    <h5> 推荐指数：${books.books_recommend}</h5>
                    <p> <em>简介：</em>${books.books_description}</p>
                </a>
            </li>
            </c:forEach>
        </ul>

<%--            <li>--%>
<%--            <a href="#">  <img src="book_imgs/book-5.png" alt="">--%>
<%--                <h5>书籍名字：Python编程 从入门到实践</h5>--%>
<%--                <h5> 推荐指数：</h5>--%>
<%--                <p><em>简介：</em> 本书内容分为 基础知识 和 项目 两部分。读完本书，读者不仅能快速掌握编程基础知识，还能编写出解决实际问题的代码并开发复杂的项目。第2版沿袭第1版讲解清晰透彻、循序渐进的特点，并全面升级。 一部分 基础知识 新增Sublime Text、f字符串、大数表示法和常量表示法等主题，并且更准确地描述了Python语言的细节。第二部分 项目 采用更简明的结构、更清晰的语法以及更流行的库和工具，如Plotly和新</p>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--            <li>--%>
<%--            <a href="#">  <img src="./book_imgs/C%231.png" alt="">--%>
<%--                <h5>书籍名字：C++项目开发实战入门（全彩版）</h5>--%>
<%--                <h5> 推荐指数：</h5>--%>
<%--                <p><em>简介：</em> 一本能让初学者通过项目实战开发学会编程的超值图书，精选实用项目，采用主流C#开发技术，让读者体验编程乐趣、获得实战经验，配同步视频教程和源代码，海量资源免费赠送</p>--%>
<%--            </a>--%>
<%--        </li>--%>
<%--            <li>--%>
<%--                <a href="#">  <img src="book_imgs/book-3.png" alt="">--%>
<%--                    <h5>书籍名字JS全书：JavaScript Web前端开发指南</h5>--%>
<%--                    <h5> 推荐指数：</h5>--%>
<%--                    <p><em>简介：</em>全面介绍JavaScript的核心语法。解读变量作用域和闭包。ES6 的新特性。前端模块化。自动化构建工具。客户端存储。使用性能优化技术来改善用户体验。</p>--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="#">  <img src="book_imgs/book-4.png" alt="">--%>
<%--                    <h5>书籍名字：C语言从入门到精通（第4版)</h5>--%>
<%--                    <h5> 推荐指数：</h5>--%>
<%--                    <p><em>简介：</em> 本书推荐： 1. 循序渐进，实战讲述：基础知识 核心技术 高级应用 项目实战，符合认知规律。 2. 168个应用实例 32个实践练习 126集高清微视频，边学边练，在实践中提升技能。 3. 超值赠送海量开发资源库资源，助力你夯实基础，精准，有效，速练，练就转岗就业不败之力。 （1）实例资源库 源码资源库：881个实例及源码详细分析，多练实例，多读源码，是快速学习之道。 （2）模块资源库：15个经典模块开发过程完整展现，</p>--%>

<%--                </a>--%>

<%--            </li>--%>

    </div>
</div>
<!--     书籍推荐部分结束-->
<!--软件推荐部分开始-->
<div class="softer">
    <div class="items_hd w">
        <h4 class="recom_name">软件</h4>
        <div class="more"><a href="#">更多</a></div>
    </div>
    <div class="softer_bd w">
        <ul>
<c:forEach items="${software}" var="software" varStatus="index">
            <li><a href="#">
                <img src="softer/soft-${index.count}.jpg" alt="">
                <h5>软件名字：${software.software_name}</h5>
                <h5> 推荐指数：${software.software_recommend}</h5>
                <p><em>优势：</em>${software.software_advantages}</p>
            </a>
            </li>
</c:forEach>
        </ul>
    </div>
</div>

<!--   footer部分-->
<div class="footer">
    <div class="footer_items">
        <ul>
            <li><a class="ceshi" href="tencent://message/?Site=baidu.com&uin=3288509748&Menu=yes">提出建议</a></li>
            <li><a href="#">网站地图</a></li>
            <li><a href="#">服务协议</a></li>
            <li><a href="http://www.waer.ltd">友情链接</a></li>
            <li><a href="#">帮助中心</a></li>
            <li><a href="#">关于我们</a></li>
        </ul>
    </div>
    <div class="copyright w">
        <span>Copyright 2021 哈尔滨商业大学XX专业版权所有</span>
        <div><img src="./logo/qiniu_%20logo.jpg" alt="">
            <p>本站由七牛提供云存储</p>
        </div>

    </div>
    <!--回到顶部开始-->
    <svg id="GoTop" t="1601108930466" class="icon1" viewBox="0 0 1365 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="3028" width="64" height="64"><path d="M675.271111 739.555556L512 1024h341.333333z" fill="#b245fe" p-id="3029"></path><path d="M682.666667 455.111111L303.217778 1024H0L682.666667 0l682.666666 1024h-303.217777z" fill="#b245fe" p-id="3030"></path></svg>
    <!--回到顶部结束-->
</div>

<script src="./bootstrap-4.4.1-dist/jquery-1.11.0.min.js"></script>

<script>
    function GoTop(minHeight){

        // 定义点击返回顶部图标后向上滚动的动画
        $("#GoTop").click(
            function(){$('html,body').animate({scrollTop:'0px'},'slow');
            })
        // 获取页面的最小高度，无传入值则默认为600像素
        minHeight? minHeight = minHeight:minHeight = 600;
        // 为窗口的scroll事件绑定处理函数
        $(window).scroll(function(){
            // 获取窗口的滚动条的垂直滚动距离
            var s = $(window).scrollTop();
            // 当窗口的滚动条的垂直距离大于页面的最小高度时，让返回顶部图标渐现，否则渐隐
            if( s > minHeight){
                $("#GoTop").fadeIn(500);
            }else{
                $("#GoTop").fadeOut(500);
            };
        });
    };
    GoTop();
    /*请求函数的ajax*/
</script>
<script src="https://cdn.jsdelivr.net/npm/jquery@3.4.1/dist/jquery.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="http://lib.sinaapp.com/js/jquery/2.0.2/jquery-2.0.2.min.js"></script>

</body>
</html>

