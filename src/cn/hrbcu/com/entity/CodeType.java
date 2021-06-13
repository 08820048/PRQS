package cn.hrbcu.com.entity;

/**
 * @author: XuYi
 * @date: 2021/5/21 22:12
 * @description: 编程语言栏目实体类
 */
public class CodeType {
    /*语言编号*/
    private int code_id;
    /*语言名称*/
    private String code_name;
    /*构造方法*/
    public CodeType() {}

    public CodeType(int code_id, String code_name) {
        this.code_id = code_id;
        this.code_name = code_name;
    }

    /*Get和Set方法*/

    public int getCode_id() {
        return code_id;
    }

    public void setCode_id(int code_id) {
        this.code_id = code_id;
    }

    public String getCode_name() {
        return code_name;
    }

    public void setCode_name(String code_name) {
        this.code_name = code_name;
    }
    /*toString方法*/

    @Override
    public String toString() {
        return "CodeType{" +
                "code_id=" + code_id +
                ", code_name='" + code_name + '\'' +
                '}';
    }
}
