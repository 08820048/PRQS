package cn.hrbcu.com.service;

import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.CodeType;
import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.entity.Software;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * @author: XuYi
 * @date: 2021/5/22 20:30
 * @description: 查询全部培训机构数据服务层接口
 */
public interface ShowService {
    /**
     * 查询全部数据--培训机构模块
     * @return InstitutionList
     */
    List<Institution> selectAllByIns();


    /**
     * 查询全部数据--书籍推荐模块
     * @return BooksList
     */
    List<Books> selectAllByBooks();

    /**
     * 查询全部数据--软件推荐模块
     * @return BooksList
     */
    List<Software> selectAllBySoft();

    /**
     * 查询全部数据--编程语言栏目展示模块
     * @return BooksList
     */
    List<CodeType> selectAllByCode();
}
