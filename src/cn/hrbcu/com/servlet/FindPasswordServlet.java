package cn.hrbcu.com.servlet;

import cn.hrbcu.com.entity.User;
import cn.hrbcu.com.service.UserService;
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
 * @date: 2021/5/29 21:47
 * @description: 通过验证用户名重置密码
 */
@WebServlet("/FindPasswordServlet")
public class FindPasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //好习惯---设置编码
        request.setCharacterEncoding("utf-8");
        //获取数据
        Map<String, String[]> map = request.getParameterMap();
        System.out.println("map="+map.size());
        //封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用服务方法
        UserService service = new UserServiceImpl();
        boolean reset = service.reset(user);
        //判断结果状态
        if (reset){
            //存入resquest
            request.setAttribute("resetMsg","<div style=\"color: orange;font-size: 20px\" >\n" +
                    "    <span style=\"color: #117a8b;font-size: 20px\"></span> <svg t=\"1608292214189\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7128\" width=\"25\" height=\"25\"><path d=\"M513 103.5C267.8 103.5 69 265 69 464.3 69 645.5 233.5 795 447.6 820.8v97.4s130.5-5.1 317.3-151c9.6-7.5 16.9-14 23-19.9 102.8-66.1 169-168.1 169-282.9 0.1-199.3-198.7-360.9-443.9-360.9zM295.6 398.6c0-32.6 26.4-59 59-59s59 26.4 59 59-26.4 59-59 59-59-26.4-59-59z m408.1 129.3c-41.3 69-93.2 107.8-154.4 115.4-7.5 0.9-14.9 1.4-22.3 1.4-106.5 0-198.2-90.5-202.4-94.6-6-6-6-15.7 0-21.7s15.7-6 21.7 0c1 1 98.3 96.8 199.3 84.5 51.2-6.4 95.6-40.3 131.7-100.7 4.4-7.3 13.8-9.6 21.1-5.3 7.3 4.3 9.6 13.7 5.3 21z m-32.4-70.3c-32.6 0-59-26.4-59-59s26.4-59 59-59 59 26.4 59 59-26.4 59-59 59z\" fill=\"#9E8EEF\" p-id=\"7129\"></path></svg>密码重置成功,请牢记~\n" +
                    "</div>");
            //重定向页面
            request.getRequestDispatcher("/findPwd.jsp").forward(request,response);
        }else{
            request.setAttribute("resetMsg","<div style=\"color: orange;font-size: 20px\" >\n" +
                    "    <span style=\"color: #117a8b;font-size: 20px\"></span> <svg t=\"1608292214189\" class=\"icon\" viewBox=\"0 0 1024 1024\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\" p-id=\"7128\" width=\"25\" height=\"25\"><path d=\"M513 103.5C267.8 103.5 69 265 69 464.3 69 645.5 233.5 795 447.6 820.8v97.4s130.5-5.1 317.3-151c9.6-7.5 16.9-14 23-19.9 102.8-66.1 169-168.1 169-282.9 0.1-199.3-198.7-360.9-443.9-360.9zM295.6 398.6c0-32.6 26.4-59 59-59s59 26.4 59 59-26.4 59-59 59-59-26.4-59-59z m408.1 129.3c-41.3 69-93.2 107.8-154.4 115.4-7.5 0.9-14.9 1.4-22.3 1.4-106.5 0-198.2-90.5-202.4-94.6-6-6-6-15.7 0-21.7s15.7-6 21.7 0c1 1 98.3 96.8 199.3 84.5 51.2-6.4 95.6-40.3 131.7-100.7 4.4-7.3 13.8-9.6 21.1-5.3 7.3 4.3 9.6 13.7 5.3 21z m-32.4-70.3c-32.6 0-59-26.4-59-59s26.4-59 59-59 59 26.4 59 59-26.4 59-59 59z\" fill=\"#9E8EEF\" p-id=\"7129\"></path></svg>用户名不存在，请检查后重试~\n" +
                    "</div>");
            request.getRequestDispatcher("/findPwd.jsp").forward(request,response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request, response);
    }
}
