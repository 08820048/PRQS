package cn.hrbcu.com.servlet.adminServlet;

import cn.hrbcu.com.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: XuYi
 * @date: 2021/5/24 20:28
 * @description:
 */
@WebServlet("/DelInsServlet")
public class DelInsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*设置编码*/
        req.setCharacterEncoding("utf-8");
        /*获取所选id*/
        String[] ins_id = req.getParameterValues("ins_id");
        /*调用服务层完成删除*/
        AdminServiceImpl adminService_del = new AdminServiceImpl();
        adminService_del.delSelectedIns(ins_id);
        /*设置跳转页面*/
        resp.sendRedirect(req.getContextPath()+"/AdminBaseServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doPost(req, resp);
    }
}
