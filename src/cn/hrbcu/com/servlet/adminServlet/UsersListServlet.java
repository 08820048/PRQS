package cn.hrbcu.com.servlet.adminServlet;

import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.Page;
import cn.hrbcu.com.entity.User;
import cn.hrbcu.com.service.BooksService;
import cn.hrbcu.com.service.UserService;
import cn.hrbcu.com.service.impl.BooksServiceImpl;
import cn.hrbcu.com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author: XuYi
 * @date: 2021/5/30 14:10
 * @description: 用户管理列表
 */
@WebServlet("/UsersListServlet")
public class UsersListServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*设置编码*/
        req.setCharacterEncoding("utf-8");
        /*获取状态参数*/
        String status = req.getParameter("status");
        /*获取当前页码*/
        String currentPage = req.getParameter("currentPage");
        /*获取总页数*/
        String totalPage = req.getParameter("totalPage");
        /*每页条数*/
        String rows = req.getParameter("rows");
        /*初始化转发路径*/
        String path = null;
//        if("PageList".equals(status)) {
        /*边界处理*/
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
            if (currentPage == null || "".equals(currentPage)) {
                currentPage = "1";
            }
        }
        if (rows == null || "".equals(rows)) {
            rows = "5";
        }
        if (currentPage == totalPage) {
            currentPage = "1";
        }
        /*获取条件查询参数*/
        Map<String, String[]> condition = req.getParameterMap();
        /*调用服务层*/
        UserService userService = new UserServiceImpl();
        Page<User> pb = userService.findUserByPage(currentPage, rows, condition);
        System.out.println(pb + "\n");
        /*往request注入值*/
        req.setAttribute("pb", pb);
        /*将查询条件注入request*/
        req.setAttribute("condition", condition);
        /*设置并转发*/
        path = "/Users.jsp";
        if("delBooks".equals(status)){
            /*获取所选id*/
            String[] id = req.getParameterValues("id");
            /*调用服务层完成删除*/
            UserService userService_del = new UserServiceImpl();
            userService_del.delSelectedUser(id);
            /*设置跳转页面*/
            resp.sendRedirect(req.getContextPath()+"/UsersListServlet");
        }
        req.getRequestDispatcher(path).forward(req,resp);
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
