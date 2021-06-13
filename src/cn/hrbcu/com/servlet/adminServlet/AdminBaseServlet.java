package cn.hrbcu.com.servlet.adminServlet;

import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.entity.Page;
import cn.hrbcu.com.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author: XuYi
 * @date: 2021/5/23 17:10
 * @description:
 */
@WebServlet("/AdminBaseServlet")
public class AdminBaseServlet extends HttpServlet {
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
            AdminServiceImpl adminService = new AdminServiceImpl();
            Page<Institution> pb = adminService.findInsByPage(currentPage, rows, condition);
            System.out.println(pb + "\n");
            /*往request注入值*/
            req.setAttribute("pb", pb);
            /*将查询条件注入request*/
            req.setAttribute("condition", condition);
            /*设置并转发*/
            path = "/Institution.jsp";
            if("delIns".equals(status)){
                /*获取所选id*/
                String[] ins_id = req.getParameterValues("ins_id");
                /*调用服务层完成删除*/
                AdminServiceImpl adminService_del = new AdminServiceImpl();
                adminService_del.delSelectedIns(ins_id);
                /*设置跳转页面*/
                resp.sendRedirect(req.getContextPath()+"/AdminBaseServlet");
            }
        req.getRequestDispatcher(path).forward(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doPost(req, resp);
    }
}
