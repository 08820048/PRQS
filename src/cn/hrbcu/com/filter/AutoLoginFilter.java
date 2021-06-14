package cn.hrbcu.com.filter;


import cn.hrbcu.com.utils.CookieUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @date: 2021/5/3 18:32
 * @description: 通过filter实现自动登录功能
 */
public class AutoLoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        /*实现逻辑：
        * 1.拦截用户登录请求，
        * 2.获取请求数据中的session和cookie
        * 3.判断是否存在正确的session和cookie数据
        * 4.是则拦截转发到主页
        * 5.否则放行拦截，执行正常登录逻辑
        * */

        Cookie[] cookies = req.getCookies();
        if(cookies !=null || cookies.length>0){
            String username = null;
            String ssid = null;
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("userKey")){
                    username = cookie.getValue();
                }
                if(cookie.getName().equals("ssid")){
                    ssid = cookie.getValue();
                }
            }
            if(username!=null && ssid != null && ssid.equals(CookieUtils.md5Encrypt(username))){
                HttpSession session  = req.getSession();
                session.setAttribute("user",username);
                resp.sendRedirect(req.getContextPath()+"/ShowBaseServlet?status=selectAllByIns");
            }else{
                chain.doFilter(req, resp);
            }
        }else{
            chain.doFilter(req, resp);
        }
    }
}