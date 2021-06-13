package cn.hrbcu.com.servlet;

import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.CodeType;
import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.entity.Software;
import cn.hrbcu.com.service.impl.ShowServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: XuYi
 * @date: 2021/5/22 19:54
 * @description: 前台数据控制层Servlet
 */

@WebServlet("/ShowBaseServlet")
public class ShowBaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*设置编码*/
        resp.setCharacterEncoding("utf-8");
        /*获取状态参数*/
        String status = req.getParameter("status");
        /*初始化转发路径*/
        String path = null;
        /*处理首页数据展示--培训机构模块*/
        if("selectAllByIns".equals(status)){
            /*初始化服务层*/
            ShowServiceImpl showService = new ShowServiceImpl();
            /*调用服务层查询方法*/
            List<Institution> institutions = showService.selectAllByIns();
            List<Books> books = showService.selectAllByBooks();
            List<Software> software = showService.selectAllBySoft();
            List<CodeType> codeTypes = showService.selectAllByCode();
            /*往request中注入值*/
            req.setAttribute("institutions",institutions);
            req.setAttribute("books",books);
            req.setAttribute("software",software);
            req.setAttribute("codeTypes",codeTypes);
            System.out.println("software测试"+books);
            System.out.println("books测试"+books);
            System.out.println("institutions测试"+institutions);
            System.out.println("codeTypes测试"+codeTypes);
            /*设置转发路径*/
            path = "/index.jsp";
        }
        /*处理首页数据展示--书籍推荐模块*/

        /*设置转发*/
        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
