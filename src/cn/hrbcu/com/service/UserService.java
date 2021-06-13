package cn.hrbcu.com.service;

import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.Page;
import cn.hrbcu.com.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author: XuYi
 * @date: 2021/5/28 8:52
 * @description:
 */
public interface UserService {
    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean regist(User user);


    boolean active(String code);

    User login(User user);

    /**
     * 重置密码
     * @param user
     * @return
     */
    boolean reset(User user);

    User AdminLogin(User user);

    /**
     * 查询全部数据--书籍推荐模块
     * @return InstitutionList
     */
    List<User> selectAllByUser();

    /**
     * 批量删除书籍信息
     *
     * @param ids
     */
    void delSelectedUser(String[] ids);

    /**
     * 分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    Page<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);

    /**
     * 修改书籍推荐信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 根据id查询书籍推荐信息
     * @param id
     * @return
     */
    Books findUserById(String id);

    /**
     * 添加书籍推荐数据信息方法服务层接口
     * @param user
     */
    void addUser(User user);

    /**
     * 添加管理员
     * @param username
     */
    void addAdmin(String username);


}
