package cn.hrbcu.com.servlet.adminServlet;

import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.service.AdminService;
import cn.hrbcu.com.service.impl.AdminServiceImpl;
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
 * @description: 处理培训机构管理数据的添加控制层
 */
@WebServlet("/AddInsServlet")
public class AddInsServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*设置编码*/
        req.setCharacterEncoding("utf-8");
        /*获取参数*/
        Map<String, String[]> map = req.getParameterMap();
        /*封装对象*/
        Institution institution = new Institution();
        try{
            BeanUtils.populate(institution,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        /*调用服务*/
        AdminService adminService = new AdminServiceImpl();
        adminService.addIns(institution);
        /*设置跳转路径*/
        String path =req.getContextPath()+"/AdminBaseServlet";
        /*执行跳转*/
        resp.sendRedirect(path);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    this.doPost(req, resp);
    }
}
