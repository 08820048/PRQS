package cn.hrbcu.com.dao;

import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author: XuYi
 * @date: 2021/5/28 8:47
 * @description: 用户信息接口
 */
public interface UserDAO {
    /**
     * 后台查询用户数据
     * @return
     */
    List<User> selectAllByUser();

    /**
     * 分页查询每页记录
     * @param start
     * @param rows
     * @param condition
     * @return
     */
    List<User> findByPage(int start, int rows, Map<String, String[]> condition);

    /**
     * 修改操作
     * @param user
     */
    void update(User user);

    /**
     * 根据编号查询书籍信息接口方法
     * @param id
     * @return
     */

    User findById(int id);

    /**
     * 添加用户数据的方法接口
     * @param user
     */
    void addUser(User user );

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



    /**
     * 用户登录
     * @param userName
     * @param userPassword
     * @return
     */
    User login(String userName, String userPassword);

    /**
     * 添加操作
     * @param user
     */
    void add(User user);

    void reset(User user);
    /**
     * 按用户名查询，用于检查用户名是否已经注册
     * @param userName
     * @return
     */
    User FindByUsername(String userName);
    // String findUserByUserName(final String userName);
    /**
     * 用户注册
     * @param user
     * @return 一个注册成功的flag
     */
    void save(User user);

    User FindByCode(String code);

    void updateStatus(User user);


    User FindByUsernameAndPassword(String username,String password);

    void addAdmin(String username);
}
