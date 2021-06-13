package cn.hrbcu.com.dao;

import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.CodeType;
import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.entity.Software;

import java.util.List;

/**
 * @author: XuYi
 * @date: 2021/5/22 19:42
 * @description: 首页数据展示模型层接口
 */

public interface ShowDAO {
    /**
     * 查询全部培训机构数据
     * @return List
     */
    List<Institution> selectAllByIns();

    /**
     * 查询全部推荐书籍数据
     * @return List
     */
    List<Books> selectAllByBooks();

    /**
     * 查询全部软件推荐数据
     * @return Soft
     */
    List<Software> selectAllBySoft();

    /**
     * 查询编程语言项目栏数据
     * @return List
     */
    List<CodeType> selectAllByCode();


}
