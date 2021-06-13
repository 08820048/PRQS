package cn.hrbcu.com.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.MessageDigest;

/**
 * @author: XuYi
 * @date: 2021/5/1 15:56
 * @description: CookieUtils工具类，用于生成和加密cookie信息
 */
public class CookieUtils {
    /*设置唯一key密钥*/
    private static final String KEY  = "FGTWDLHNN8";

    /**
     * 创建指定cookie信息
     * @param username 用户的用户名：放入cookie的信息
     * @param req
     * @param resp 添加cookie
     * @param sec 设置cookie有效期
     */
    public static void createCookie(String username, HttpServletRequest req, HttpServletResponse resp,int sec){
        Cookie userCookie = new Cookie("userKey",username);
        Cookie ssidCookie = new Cookie("ssid", md5Encrypt(username));
        userCookie.setMaxAge(sec);
        ssidCookie.setMaxAge(sec);
//        userCookie.setPath("/");
//        ssidCookie.setPath("/");
        resp.addCookie(userCookie);
        resp.addCookie(ssidCookie);
    }

    public static  String md5Encrypt(String s){
        s = s==null ? "" : s+KEY;
        char[]  md5Digest ={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        try {
            /*JDK自带MD5加密库*/
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] sarr =s.getBytes();
            /*把明文放到加密类MessageDigest的对象实例，更新数据*/
            md.update(sarr);
            byte[] marr = md.digest();
            int len = marr.length;
            char[] str = new char[len*2];
            /*记数*/
            int k = 0;
            for (int i = 0; i < len; i++) {
                byte b =marr[i];
                str[k++] = md5Digest[b>>>4 & 0xf];
                str[k++] = md5Digest[b & 0xf];
            }
            return new String(str);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }
}
