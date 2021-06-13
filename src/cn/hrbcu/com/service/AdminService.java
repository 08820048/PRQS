package cn.hrbcu.com.service;

import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.entity.Page;

import java.util.List;
import java.util.Map;

/**
 * @author: XuYi
 * @date: 2021/5/23 18:09
 * @description: 后台管理数据的服务接口方法
 */
public interface AdminService {


    /**
     * 查询全部数据--培训机构模块
     * @return InstitutionList
     */
    List<Institution> selectAllByIns();

    /**
     * 批量删除机构信息
     *
     * @param ids
     */
    void delSelectedIns(String[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    Page<Institution> findInsByPage(String currentPage, String rows, Map<String, String[]> condition);

    /**
     * 修改培训机构信息
     * @param institution
     */
    void updateIns(Institution institution);

    /**
     * 根据id查询培训机构信息
     * @param id
     * @return
     */
    Institution findInsById(String id);

    /**
     * 添加培训机构数据信息方法服务层接口
     * @param institution
     */
    void addIns(Institution institution);

}
