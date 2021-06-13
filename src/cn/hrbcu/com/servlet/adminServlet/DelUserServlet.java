package cn.hrbcu.com.servlet.adminServlet;

import cn.hrbcu.com.service.UserService;
import cn.hrbcu.com.service.impl.AdminServiceImpl;
import cn.hrbcu.com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: XuYi
 * @date: 2021/5/24 20:28
 * @description: 闪出用户
 */
@WebServlet("/DelUserServlet")
public class DelUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*设置编码*/
        req.setCharacterEncoding("utf-8");
        /*获取所选id*/
        String[] id = req.getParameterValues("id");
        /*调用服务层完成删除*/
        UserService userService = new UserServiceImpl();
        userService.delSelectedUser(id);
        /*设置跳转页面*/
        resp.sendRedirect(req.getContextPath()+"/UsersListServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doPost(req, resp);
    }
}
