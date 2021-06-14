package cn.hrbcu.com.service.impl;

import cn.hrbcu.com.dao.UserDAO;
import cn.hrbcu.com.dao.impl.UserDAOImpl;
import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.Page;
import cn.hrbcu.com.entity.User;
import cn.hrbcu.com.service.UserService;
import cn.hrbcu.com.utils.MailUtils;
import cn.hrbcu.com.utils.MD5andSHA;
import cn.hrbcu.com.utils.UuidUtil;

import java.util.List;
import java.util.Map;


public class UserServiceImpl implements UserService {
   private UserDAO dao = new UserDAOImpl();
    /**
     * 用户注册
     * @param user
     * @return
     */
    @Override
    public boolean regist(User user) {
        /*根据用户名查询用户对象*/
        User u =dao.FindByUsername(user.getUsername());
        /*判断user是否为空*/
        if(u!=null){
            /*说明用户名已经存在*/
            return false;
        }
        /*否则保存用户的注册信息*/
        /*设置用户唯一识别码*/
        user.setCode(UuidUtil.getUuid());
        /*设置用户激活状态*/
        user.setStatus("N");
        dao.save(user);
        /*发送邮件*/
//        String content ="帐号:"+user.getUsername()+":注册成功，请点击链接进行激活:< a href='http://127.0.0.1:8081/PRQS/ActiveUserServlet?code="+user.getCode()+"'>立即激活</ a>";
        String content ="帐号:"+user.getUsername()+":注册成功，请点击链接进 行激活:<a href='http://127.0.0.1:8081/PRQS/ActiveUserServlet?code="+ user.getCode()+"'>立即激活</a>";

        MailUtils.sendMail(user.getEmail(),content,"[编程资源网]激活邮件(无需回复)");
        return true;
    }

    /**
     * 激活账号
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        User user = dao.FindByCode(code);
        if(user!=null){
            dao.updateStatus(user);
            return true;
        }
        return false;
    }

    @Override
    public User login(User user) {
        /*2.调用dao层登录方法*/
        return dao.FindByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public boolean reset(User user) {
        System.out.println("服务层实现中的getUsername"+user.getUsername());
        if (dao.FindByUsername(user.getUsername()) == null) {
            return false;
        }else {
            dao.reset(user);
            return true;
        }
    }

    @Override
    public User AdminLogin(User user) {
       return dao.login(user.getUsername(), user.getPassword());
    }

    @Override
    public List<User> selectAllByUser() {
        return dao.selectAllByUser() ;

    }

    @Override
    public void delSelectedUser(String[] ids) {
        if(ids!=null && ids.length > 0){
            for (String id : ids) {
                dao.delete(Integer.parseInt(id));
            }
        }
    }

    @Override
    public Page<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);
        //判断页码边界的点击操作的校验
        if (currentPage<=0){
            currentPage=1;
        }
        //创建空的pageBean对象
        Page<User> pb = new Page<User>();
        //设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);
        //调用DAO查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //调用dao查询list集合
        //计算开始的记录索引
        int start = (currentPage-1)*rows;
        List<User> list = dao.findByPage(start,rows,condition);
        pb.setList(list);
        //计算总页码
        int totalPage = (totalCount%rows)==0 ?totalCount/rows:(totalCount/rows)+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public Books findUserById(String id) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void addAdmin(String username) {
       dao.addAdmin(username);
    }

}
