package cn.hrbcu.com.service.impl;

import cn.hrbcu.com.dao.AdminDAO;
import cn.hrbcu.com.dao.ShowDAO;
import cn.hrbcu.com.dao.impl.AdminDAOImpl;
import cn.hrbcu.com.dao.impl.ShowDAOImpl;
import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.entity.Page;
import cn.hrbcu.com.service.AdminService;

import java.util.List;
import java.util.Map;

/**
 * @author: XuYi
 * @date: 2021/5/23 18:11
 * @description:
 */
public class AdminServiceImpl implements AdminService {
    /*实现创建dao层对象*/
   AdminDAO dao =  new AdminDAOImpl();

    @Override
    public List<Institution> selectAllByIns() {
        return dao.selectAllByIns();
    }

    /**
     * 多选删除的方法实现
     * @param ids
     */
    @Override
    public void delSelectedIns(String[] ids) {
        if(ids!=null && ids.length > 0){
            for (String id : ids) {
                dao.delete(Integer.parseInt(id));
            }
        }
    }


    @Override
    public Page<Institution> findInsByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //判断页码边界的点击操作的校验
        if (currentPage<=0){
            currentPage=1;
        }
        //创建空的pageBean对象
        Page<Institution> pb = new Page<Institution>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //调用DAO查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //调用dao查询list集合
        //计算开始的记录索引
        int start = (currentPage-1)*rows;
        List<Institution> list = dao.findByPage(start,rows,condition);
        pb.setList(list);
        //计算总页码
        int totalPage = (totalCount%rows)==0 ?totalCount/rows:(totalCount/rows)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 服务层更新方法接口的实现
     * @param institution
     */
    @Override
    public void updateIns(Institution institution) {
        dao.update(institution);
    }

    /**
     * id查询方法服务接口实现
     * @param id
     * @return
     */
    @Override
    public Institution findInsById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    /**
     * 数据添加服务层接口方法实现
     * @param institution
     */
    @Override
    public void addIns(Institution institution) {
         dao.addIns(institution);
    }
}
