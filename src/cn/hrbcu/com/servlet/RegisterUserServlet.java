package cn.hrbcu.com.servlet;

import cn.hrbcu.com.entity.ResultInfo;
import cn.hrbcu.com.entity.User;
import cn.hrbcu.com.service.UserService;
import cn.hrbcu.com.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
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
 * @date: 2021/5/28 8:51
 * @description:
 */
@WebServlet("/RegisterUserServlet")
public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        String check = request.getParameter("check");
        HttpSession session = request.getSession();
        String checkcode = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        System.out.println("check"+check);
        System.out.println("checkcode"+checkcode);
        if (check==null || !checkcode.equalsIgnoreCase(check)){
            //验证码错误
            request.setAttribute("register_msg","<div style=\"color: orange;font-size: 20px\" >\n" +
                    "    <span style=\"color: #117a8b;font-size: 20px\">[</span><span style=\"color: red;font-size: 15px\">~_~</span><span style=\"color: #117a8b;font-size: 20px\">]</span> 验证码错误!请重试~\n" +
                    "</div>");
            request.getRequestDispatcher("/register_new.jsp").forward(request, response);
            return;
        }
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();
        System.out.println("user"+user);
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("map"+map.size());
        //3.注册前的用户名检查,排除已经注册过的用户名
        UserService userService = new UserServiceImpl();
        boolean flag = userService.regist(user);
        System.out.println("flag"+flag);
        // int flag = userService.register_user(user);
        if (flag){//注册成功
            //request.setAttribute("register_msg","<h1>注册成功[^_^],即将跳转到登陆页!</h1>");
            request.getRequestDispatcher("/register_successfully.jsp").forward(request,response);
        }else {
            //注册失败
            request.setAttribute("register_msg","<p style=\"color:orange\">这个用户名太抢手,换一个试试吧[^_^]</p>");
            request.getRequestDispatcher("/register_new.jsp").forward(request,response);
            return;
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
