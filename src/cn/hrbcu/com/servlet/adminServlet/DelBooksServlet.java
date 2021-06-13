package cn.hrbcu.com.servlet.adminServlet;

import cn.hrbcu.com.service.BooksService;
import cn.hrbcu.com.service.impl.AdminServiceImpl;
import cn.hrbcu.com.service.impl.BooksServiceImpl;

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
@WebServlet("/DelBooksServlet")
public class DelBooksServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*设置编码*/
        req.setCharacterEncoding("utf-8");
        /*获取所选id*/
        String[] books_id = req.getParameterValues("books_id");
        /*调用服务层完成删除*/
        BooksService booksService_del = new BooksServiceImpl();
        booksService_del.delSelectedBooks(books_id);
        /*设置跳转页面*/
        resp.sendRedirect(req.getContextPath()+"/BooksListServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doPost(req, resp);
    }
}
