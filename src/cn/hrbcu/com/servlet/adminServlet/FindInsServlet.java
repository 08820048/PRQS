package cn.hrbcu.com.servlet.adminServlet;

import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.service.impl.AdminServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: XuYi
 * @date: 2021/5/25 22:16
 * @description:
 */
@WebServlet("/FindInsServlet")
public class FindInsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取aid
        String ins_id = req.getParameter("ins_id");
        //调用service查询
        AdminServiceImpl service = new AdminServiceImpl();
        Institution ins = service.findInsById(ins_id);
        //将article存入request
        req.setAttribute("ins",ins);
        //转发到InsUpdate.jsp
        req.getRequestDispatcher("/InsUpdate.jsp").forward(req,resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doPost(req, resp);
    }
}
