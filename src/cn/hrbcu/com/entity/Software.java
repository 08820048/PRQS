package cn.hrbcu.com.entity;

/**
 * @author: XuYi
 * @date: 2021/5/21 22:05
 * @description: 软件推荐实体类
 */
public class Software {
    /*软件编号*/
    private int software_id;
    /*软件名称*/
    private String software_name;
    /*推荐指数*/
    private String software_recommend;
    /*软件优势*/
    private String software_advantages;
    /*构造方法*/
    public Software() {}

    public Software(int software_id, String software_name, String software_recommend, String software_advantages) {
        this.software_id = software_id;
        this.software_name = software_name;
        this.software_recommend = software_recommend;
        this.software_advantages = software_advantages;
    }

    /*Get和Set方法*/

    public int getSoftware_id() {
        return software_id;
    }

    public void setSoftware_id(int software_id) {
        this.software_id = software_id;
    }

    public String getSoftware_name() {
        return software_name;
    }

    public void setSoftware_name(String software_name) {
        this.software_name = software_name;
    }

    public String getSoftware_recommend() {
        return software_recommend;
    }

    public void setSoftware_recommend(String software_recommend) {
        this.software_recommend = software_recommend;
    }

    public String getSoftware_advantages() {
        return software_advantages;
    }

    public void setSoftware_advantages(String software_advantages) {
        this.software_advantages = software_advantages;
    }
    /*toString方法*/

    @Override
    public String toString() {
        return "Software{" +
                "software_id=" + software_id +
                ", software_name='" + software_name + '\'' +
                ", software_recommend='" + software_recommend + '\'' +
                ", software_advantages='" + software_advantages + '\'' +
                '}';
    }
}
