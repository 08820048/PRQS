package cn.hrbcu.com.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author: XuYi
 * @date: 2021/5/29 20:29
 * @description:
 */
@WebServlet("/LoginOutServlet")
public class LoginOutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*获取cookie*/
        Cookie[] cookies = request.getCookies();
        /*遍历删除cookie*/
        if(cookies != null || cookies.length>0){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userKey")){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
                if (cookie.getName().equals("ssid")){
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }

        /*获取session*/
        HttpSession session = request.getSession();
        /*删除session*/
        if (session!=null){
            session.removeAttribute("user");
        }
        /*注销成功：定向到登录页面*/
        response.sendRedirect(request.getContextPath()+"/login.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
