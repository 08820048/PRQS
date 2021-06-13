package cn.hrbcu.com.servlet.adminServlet;

import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.entity.User;
import cn.hrbcu.com.service.AdminService;
import cn.hrbcu.com.service.UserService;
import cn.hrbcu.com.service.impl.AdminServiceImpl;
import cn.hrbcu.com.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: XuYi
 * @date: 2021/5/26 15:10
 * @description: 添加管理员
 */
@WebServlet("/AddAdminServlet")
public class AddAdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*设置编码*/
        req.setCharacterEncoding("utf-8");
        /*获取参数*/
        Map<String, String[]> map = req.getParameterMap();
        /*封装对象*/
        User user = new User();
        try{
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e){
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        /*调用服务*/
        UserService userService = new UserServiceImpl();
        userService.addAdmin(user.getUsername());
        /*设置跳转路径*/
        String path =req.getContextPath()+"/UsersListServlet";
        /*执行跳转*/
        resp.sendRedirect(path);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doPost(req, resp);
    }
}
