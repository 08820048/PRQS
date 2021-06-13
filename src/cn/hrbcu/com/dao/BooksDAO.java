package cn.hrbcu.com.dao;

import cn.hrbcu.com.entity.Books;

import java.util.List;
import java.util.Map;

/**
 * @author: XuYi
 * @date: 2021/5/26 18:03
 * @description:
 */
public interface BooksDAO {

    /**
     * 后台查询书籍推荐数据
     * @return
     */
    List<Books> selectAllByBooks();

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<Books> findByPage(int start, int rows, Map<String, String[]> condition);

    /**
     * 修改操作
     * @param books
     */
    void update(Books books);

    /**
     * 根据编号查询书籍信息接口方法
     * @param id
     * @return
     */

    Books findById(int id);

    /**
     * 添加书籍推荐数据的方法接口
     * @param books
     */
    void addBooks(Books books);

    /**
     * 删除操作
     *
     * @param id
     */
    void delete(int id);

    /**
     * 查询总记录数
     *
     * @param condition
     * @return
     */
    int findTotalCount(Map<String, String[]> condition);
}
