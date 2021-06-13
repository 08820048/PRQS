package cn.hrbcu.com.service;

import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.entity.Page;

import java.util.List;
import java.util.Map;

/**
 * @author: XuYi
 * @date: 2021/5/26 18:31
 * @description: 书籍推荐服务层接口
 */
public interface BooksService {
    /**
     * 查询全部数据--书籍推荐模块
     * @return InstitutionList
     */
    List<Books> selectAllByBooks();

    /**
     * 批量删除书籍信息
     *
     * @param ids
     */
    void delSelectedBooks(String[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    Page<Books> findBooksByPage(String currentPage, String rows, Map<String, String[]> condition);

    /**
     * 修改书籍推荐信息
     * @param books
     */
    void updateBooks(Books books );

    /**
     * 根据id查询书籍推荐信息
     * @param id
     * @return
     */
    Books findBooksById(String id);

    /**
     * 添加书籍推荐数据信息方法服务层接口
     * @param books
     */
    void addBooks(Books books);



}
