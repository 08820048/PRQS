package cn.hrbcu.com.dao;

import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.Institution;

import java.util.List;
import java.util.Map;

/**
 * @date: 2021/5/22 19:42
 * @description: 后台管理的模型层接口
 */
public interface AdminDAO {

    /**
     * 删除操作
     *
     * @param id
     */
    void delete(int id);

    /**
     * 后台查询培训机构数据
     *
     * @return
     */
    List<Institution> selectAllByIns();

    /**
     * 查询总记录数
     *
     * @param condition
     * @return
     */
    int findTotalCount(Map<String, String[]> condition);

    /**
     * 分页查询每页记录
     *
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<Institution> findByPage(int start, int rows, Map<String, String[]> condition);

    /**
     * 修改操作
     *
     * @param institution
     */
    void update(Institution institution);

    /**
     * 根据编号查询机构信息接口方法
     *
     * @param id
     * @return
     */
    Institution findById(int id);

    /**
     * 添加培训机构数据的方法接口
     *
     * @param institution
     */
    void addIns(Institution institution);
}



