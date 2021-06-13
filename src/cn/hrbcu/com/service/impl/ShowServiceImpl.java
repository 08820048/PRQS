package cn.hrbcu.com.service.impl;

import cn.hrbcu.com.dao.ShowDAO;
import cn.hrbcu.com.dao.impl.ShowDAOImpl;
import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.CodeType;
import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.entity.Software;
import cn.hrbcu.com.service.ShowService;

import java.util.List;

/**
 * @author: XuYi
 * @date: 2021/5/22 20:31
 * @description: 查询全部数据服务层实现
 */
public class ShowServiceImpl implements ShowService {
    /*实现创建dao层对象*/
    ShowDAO dao =new ShowDAOImpl();

    /**
     * 查询全部数据--培训机构模块的实现
     * @return
     */
    @Override
    public List<Institution> selectAllByIns() {
        return dao.selectAllByIns();
    }

    /**
     * 查询全部数据--书籍推荐模块的实现
     * @return
     */
    @Override
    public List<Books> selectAllByBooks() {
        return dao.selectAllByBooks();
    }

    @Override
    public List<Software> selectAllBySoft() {
        return dao.selectAllBySoft();
    }

    @Override
    public List<CodeType> selectAllByCode() {
        return dao.selectAllByCode();
    }
}
