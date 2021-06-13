package cn.hrbcu.com.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: XuYi
 * @date: 2021/5/3 19:00
 * @description:
 */
public class IsLoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        /*截取请求路径*/
        String path = req.getServletPath().substring(1);
        System.out.println("path="+path);
        /*获取拦截配置信息*/
        String autho = getFilterConfig().getInitParameter("authority");
        String noautho = getFilterConfig().getInitParameter("noautho");
        System.out.println("noautho="+noautho);
        /*处理字符串--拼接*/
        String[] strArr = autho.split(",");
        String[] noautoArr = noautho.split(",");


        /*转为字符数组
        * 无需权限页面
        * */
        for (String str : noautoArr) {
            if(path.equals(str)){
                chain.doFilter(req, resp);
            }
        }

        HttpSession session = req.getSession();
        for (String str : strArr) {
            if (str.equals(path)){
             String username =(String) session.getAttribute("user");
            if(username!=null){
                chain.doFilter(req, resp);
            }else{
                resp.sendRedirect(req.getContextPath()+"/login.jsp");
            }
            }
        }
    }
}
