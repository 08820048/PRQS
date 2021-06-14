package cn.hrbcu.com.entity;

/**
 * @date: 2021/5/27 22:41
 * @description: 用户信息实体类
 */
public class User {
    /*用户编号*/
    private int id;
    /*用户名*/
    private String username;
    /*手机号*/
    private String telephone;
    /*邮箱*/
    private String email;
    /*密码*/
    private String password;
    /*管理员识别码*/
    private String mid;
    /*激活状态码*/
    private String status;
    /*激活码*/
    private String code;

    /*构造方法*/
    public  User() {

    }

    public User(int id, String username, String telephone, String email, String password, String mid, String status, String code) {
        this.id = id;
        this.username = username;
        this.telephone = telephone;
        this.email = email;
        this.password = password;
        this.mid = mid;
        this.status = status;
        this.code = code;
    }
    /*Get和Set方法*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    /*toString方法*/

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mid='" + mid + '\'' +
                ", status='" + status + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
