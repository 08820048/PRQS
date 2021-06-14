package cn.hrbcu.com.servlet;

import cn.hrbcu.com.service.UserService;
import cn.hrbcu.com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @date: 2021/5/28 22:24
 * @description: 帐号激活
 */
@WebServlet("/ActiveUserServlet")
public class activeUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*设置编码*/
        request.setCharacterEncoding("utf-8");
        /*获取激活码*/
        String code = request.getParameter("code");
        System.out.println("code"+code);
        if(code!=null){
            /*调用服务层*/
            UserService userService = new UserServiceImpl();
            boolean  flag = userService.active(code);
            String msgTag = null;
            System.out.println("激活fkag"+flag);
            if(flag){
                /*激活成功*/
                System.out.println("激活成功");
                msgTag= "激活成功，请<a href=\"login.jsp\">登录</a>";
                request.setAttribute("active_msg",msgTag);
                request.getRequestDispatcher("/activeSuccess.jsp").forward(request, response);
            }else {
                msgTag = "激活失败,请联系网站请<a class=\"ceshi\" href=\"tencent://message/?Site=baidu.com&uin=2487534069&Menu=yes\"管理员</a>\n";
                request.setAttribute("active_msg", msgTag);
                request.getRequestDispatcher("/activeSuccess.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
