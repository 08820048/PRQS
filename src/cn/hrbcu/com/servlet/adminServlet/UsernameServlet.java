package cn.hrbcu.com.servlet.adminServlet;

import cn.hrbcu.com.entity.User;
import cn.hrbcu.com.service.UserService;
import cn.hrbcu.com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: XuYi
 * @date: 2021/5/30 19:01
 * @description:
 */
@WebServlet("/UsernameServlet")
public class UsernameServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*设置编码*/
        request.setCharacterEncoding("utf-8");
        /*获取参数*/
        String username = request.getParameter("username");
        /*调用服务层查询*/
        UserService userService = new UserServiceImpl();
        List<User> users = userService.selectAllByUser();
        request.setAttribute("users",users);
        /*转发页面*/
        request.getRequestDispatcher("/AdminAdd.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
