package cn.hrbcu.com.service.impl;

import cn.hrbcu.com.dao.AdminDAO;
import cn.hrbcu.com.dao.BooksDAO;
import cn.hrbcu.com.dao.impl.AdminDAOImpl;
import cn.hrbcu.com.dao.impl.BooksDAOImpl;
import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.entity.Page;
import cn.hrbcu.com.service.BooksService;

import java.util.List;
import java.util.Map;

/**
 * @author: XuYi
 * @date: 2021/5/26 18:42
 * @description:
 */
public class BooksServiceImpl implements BooksService {
    /*实现创建dao层对象*/
    BooksDAO dao =  new BooksDAOImpl();
    @Override
    public List<Books> selectAllByBooks() {
        return dao.selectAllByBooks() ;
    }

    @Override
    public void delSelectedBooks(String[] ids) {
        if(ids!=null && ids.length > 0){
            for (String id : ids) {
                dao.delete(Integer.parseInt(id));
            }
        }
    }

    @Override
    public Page<Books> findBooksByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //判断页码边界的点击操作的校验
        if (currentPage<=0){
            currentPage=1;
        }
        //创建空的pageBean对象
        Page<Books> pb = new Page<Books>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //调用DAO查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //调用dao查询list集合
        //计算开始的记录索引
        int start = (currentPage-1)*rows;
        List<Books> list = dao.findByPage(start,rows,condition);
        pb.setList(list);
        //计算总页码
        int totalPage = (totalCount%rows)==0 ?totalCount/rows:(totalCount/rows)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public void updateBooks(Books books) {
        dao.update(books);
    }

    @Override
    public Books findBooksById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    public void addBooks(Books books) {
        dao.addBooks(books);
    }
}
