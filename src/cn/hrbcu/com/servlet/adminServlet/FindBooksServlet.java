package cn.hrbcu.com.servlet.adminServlet;

import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.Institution;
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
 * @date: 2021/5/25 22:16
 * @description:
 */
@WebServlet("/FindBooksServlet")
public class FindBooksServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取aid
        String books_id = req.getParameter("books_id");
        //调用service查询
      BooksService service = new BooksServiceImpl();
        Books books = service.findBooksById(books_id);
        //将article存入request
        req.setAttribute("books",books);
        //转发到InsUpdate.jsp
        req.getRequestDispatcher("/BooksUpdate.jsp").forward(req,resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doPost(req, resp);
    }
}
