package cn.hrbcu.com.servlet.adminServlet;

import cn.hrbcu.com.entity.User;
import cn.hrbcu.com.service.UserService;
import cn.hrbcu.com.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: XuYi
 * @date: 2021/5/30 10:54
 * @description:
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        //3.获取时候自动登录的状态
        HttpSession session = request.getSession();
        Map<String, String[]> map = request.getParameterMap();
        //3.封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        }catch (InvocationTargetException e){
            e.printStackTrace();
        }
        //5.调用Servicec查询
        UserService userService = new UserServiceImpl();
        User loginUser = userService.AdminLogin(user);
        if(loginUser==null){
            request.setAttribute("adminlogin_msg","<div style=\"color: orange;font-size: 20px\" >\n" +
                    "    <span style=\"color: #117a8b;font-size: 20px\">[</span><span style=\"color: red;font-size: 15px\">~_~</span><span style=\"color: #117a8b;font-size: 20px\">]</span>账号或密码错误,请确认后重试\n" +
                    "</div>");
            request.getRequestDispatcher("/AdminLogin.jsp").forward(request,response);
        }
        if (loginUser!=null && !"Y".equals(loginUser.getMid())) {
            //session.setAttribute("admin", loginUser.getUsername());
            request.setAttribute("adminlogin_msg","<div style=\"color: orange;font-size: 20px\" >\n" +
                    "    <span style=\"color: #117a8b;font-size: 20px\">[</span><span style=\"color: red;font-size: 15px\">~_~</span><span style=\"color: #117a8b;font-size: 20px\">]</span>您不是管理员,无权访问！\n" +
                    "</div>");
            request.getRequestDispatcher("/AdminLogin.jsp").forward(request,response);
        }
        if (loginUser!=null && "Y".equals(loginUser.getMid()) && !"Y".equals(loginUser.getStatus())) {
            //session.setAttribute("admin", loginUser.getUsername());
            request.setAttribute("adminlogin_msg","<div style=\"color: orange;font-size: 20px\" >\n" +
                    "    <span style=\"color: #117a8b;font-size: 20px\">[</span><span style=\"color: red;font-size: 15px\">~_~</span><span style=\"color: #117a8b;font-size: 20px\">]</span>哦豁,该帐号未激活,无法登录！\n" +
                    "</div>");
            request.getRequestDispatcher("/AdminLogin.jsp").forward(request,response);
        }
        if (loginUser!=null && "Y".equals(loginUser.getMid()) && "Y".equals(loginUser.getStatus())) {
            session.setAttribute("user", loginUser.getUsername());
            request.getRequestDispatcher("/AdminIndex.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
