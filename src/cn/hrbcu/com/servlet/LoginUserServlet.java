package cn.hrbcu.com.servlet;

import cn.hrbcu.com.entity.User;
import cn.hrbcu.com.service.UserService;
import cn.hrbcu.com.service.impl.UserServiceImpl;
import cn.hrbcu.com.utils.CookieUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: XuYi
 * @date: 2021/5/29 9:41
 * @description: 用户登录
 */
@WebServlet("/loginUserServlet")
public class LoginUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*设置编码*/
        request.setCharacterEncoding("utf-8");
        /*获取参数*/
        String vcode = request.getParameter("vcode_l");
        String auto_login = request.getParameter("auto_login");
        System.out.println("前端验证码"+vcode);
        Map<String,String[]> map = request.getParameterMap();
        /*封装对象*/
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.校验验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String)session.getAttribute("CHECKCODE_SERVER");
        //保证验证码的时效性---确保一次性
        session.removeAttribute("CHECKCODE_SERVER");
        System.out.println("目标验证码"+checkcode_server);
        if (!checkcode_server.equalsIgnoreCase(vcode)){
           /*验证码不正确，回写提示信息*/
            request.setAttribute("login_msg","<div style=\"color: orange;font-size: 20px\" >\n" +
                    "    <span style=\"color: #117a8b;font-size: 20px\">[</span><span style=\"color: red;font-size: 15px\">~_~</span><span style=\"color: #117a8b;font-size: 20px\">]</span> 验证码错误!请重试~\n" +
                    "</div>");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        /*获取cookie对象*/
        Cookie[] cookies = request.getCookies();
        /*是否登录的标记：true表示已经登录*/
        boolean login = false;
        /*登录账号*/
        String account = null;
        /*cookie标识，用于判定是否登录成功*/
        String ssid = null;
        /*遍历查找cookie记录*/
        if(cookies!=null && cookies.length > 0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userKey")){
                    /*为账号赋值*/
                    account = cookie.getValue();
                }
                if(cookie.getName().equals("ssid")){
                    /*为ssid赋值*/
                    ssid = cookie.getValue();
                }
            }
        }
        if(account!=null && ssid != null){
            login = ssid.equals(CookieUtils.md5Encrypt(user.getUsername()));
        }
        System.out.println("login:"+login);
        /*首次登录*/
        UserService userService = new UserServiceImpl();
        User loginUser = userService.login(user);
        System.out.println("kzcuserlogin:"+loginUser);
       // System.out.println("控制层密码:"+loginUser.getPassword());
        /*如果用户还未登录*/
        if(!login){
            System.out.println("非login:"+login);
            if(loginUser==null){
                /*写回错误提示信息*/
                request.setAttribute("login_msg","<div style=\"color: orange;font-size: 20px\" >\n" +
                        "    <span style=\"color: #117a8b;font-size: 20px\">[</span><span style=\"color: red;font-size: 15px\">~_~</span><span style=\"color: #117a8b;font-size: 20px\">]</span> 用户名或密码错误,请确认后重试\n" +
                        "</div>");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            if(loginUser!=null && !"Y".equals(loginUser.getStatus())){
                /*写回错误提示信息*/
                request.setAttribute("login_msg","<div style=\"color: orange;font-size: 20px\" >\n" +
                        "    <span style=\"color: #117a8b;font-size: 20px\">[</span><span style=\"color: red;font-size: 15px\">~_~</span><span style=\"color: #117a8b;font-size: 20px\">]</span>帐号未激活,激活后重试\n" +
                        "</div>");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
                return;
            }
            /*用户信息匹配,成功登录*/
            if(loginUser!=null && "Y".equals(loginUser.getStatus())){
                auto_login = auto_login==null ? "" : auto_login;
                /*为该账号创建cookie*/
                System.out.println("if中的loginuser:"+loginUser);
                switch (auto_login){
                    case "0":
                        /*创建一个cookie,为期：一万年*/
                        CookieUtils.createCookie(user.getUsername(),request,response,10000*365*24*60*60);
                        break;
                    default :
                        /*创建一个临时cookie：有效期：关闭浏览器即销毁*/
                        CookieUtils.createCookie(user.getUsername(),request,response,-1);
                        break;
                }
                /*写入session,用于是否登录的判断*/
                request.getSession().setAttribute("user",user.getUsername());
                /*登录成功，允许进入主页*/
               response.sendRedirect(request.getContextPath()+"/ShowBaseServlet?status=selectAllByIns");
            }
        }else {
            /*已有登录记录，直接放行进入首页*/
            request.getSession().setAttribute("user",user.getUsername());
            response.sendRedirect(request.getContextPath()+"/ShowBaseServlet?status=selectAllByIns");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
