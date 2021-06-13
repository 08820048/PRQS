package cn.hrbcu.com.dao.impl;

import cn.hrbcu.com.dao.UserDAO;
import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.User;
import cn.hrbcu.com.utils.JDBCUtils;
import cn.hrbcu.com.utils.MD5andSHA;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: XuYi
 * @date: 2021/5/28 8:47
 * @description: 用户信息接口实现
 */
public class UserDAOImpl implements UserDAO {
    /*创建jdbcTemplate连接对象--类全局使用*/
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) {
        User user = null;
        try {
            String sql = "select * from user where username=? and password=?";
            user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,MD5andSHA.sha(password));
            System.out.println("DAOimpl中密码加密结果："+password);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return user;
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void delete(int id) {
        /*编写sql语句*/
        String sql = "delete from user where id=?";
        /*执行操作*/
        template.update(sql,id);
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> keySet = condition.keySet();
        //定义一个参数集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());

    }

    @Override
    public User findById(int id) {
        /*编写sql语句*/
        String sql = "select * from user where id=?";
        /*执行语句查询并返回结果*/
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),id);
}

    @Override
    public void addUser(User user) {

    }

    @Override
    public List<User> selectAllByUser() {
        /*编写sql语句*/
        String sql = "select * from user";
        /*执行查询*/
        List<User> user = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        /*返回结果*/
        return user;
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from user where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> keySet = condition.keySet();
        //定义一个参数集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }
        //添加分页的查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<User>(User.class),params.toArray());

    }

    @Override
    public void update(User user) {

    }

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    @Override
    public User FindByUsername(String username) {
        System.out.println("DAOIMPL中的username参数"+username);
       User user = null;
        try {
            String sql = "select * from user where username=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        //System.out.println("DAOIMPL中的user="+user);
        return user;
    }

    /**
     * 保存用户注册信息
     * @param user
     */
    @Override
    public void save(User user) {
        String sql = "insert into user(username,telephone,email,password,status,code) values(?,?,?,?,?,?)";
        template.update(sql,
                user.getUsername(),
                user.getTelephone(),
                user.getEmail(),
                //对用户密码进行哈希安全加密
                MD5andSHA.sha(user.getPassword()),
                user.getStatus(),
                user.getCode()
        );
    }


    /**
     * 密码重置
     * @param user
     */
    @Override
    public void reset(User user) {
        String sql = "update user set password=? where username=? ";
        template.update(sql,
                MD5andSHA.sha(user.getPassword()),
                user.getUsername()
        );
    }

    /**
     * 查找激活码
     * @param code
     * @return
     */
    @Override
    public User FindByCode(String code) {
    User user = null;
    try {
        String sql = "select * from user where code = ?";
        user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),code);
    }catch (DataAccessException e){
        e.printStackTrace();
    }
    return user;
    }

    /**
     * 更新激活状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
    String sql = "update user set status='Y' where id=?";
    template.update(sql,user.getId());
    }


    /**
     * 查找用户名和密码
     * @param username
     * @param password
     * @return
     */
    @Override
    public User FindByUsernameAndPassword(String username, String password) {
        User user =null;
        try {
            String sql = "select * from user where username= ? and password= ?";
            user= template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),username,MD5andSHA.sha(password));
        }catch (Exception e) {

        }
        return user;
    }

    /**
     * 添加管理员
     * @param username
     */
    @Override
    public void addAdmin(String username) {
        String sql = "update user set mid='Y' where username=?";
        template.update(sql,username);
    }
}
