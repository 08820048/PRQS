package cn.hrbcu.com.entity;

/**
 * @author: XuYi
 * @date: 2021/5/21 21:57
 * @description: 培训机构推荐实体类
 */
public class Institution {
    /*机构编号*/
    private int ins_id;
    /*机构名称*/
    private String ins_name;
    /*推荐指数*/
    private String ins_recommend;
    /*机构概述*/
    private String ins_description;
    /*修改日期*/
    private String ins_date;
    /*构造方法*/
    public Institution() {}

    public Institution(int ins_id, String ins_name, String ins_recommend, String ins_description,String ins_date) {
        this.ins_id = ins_id;
        this.ins_name = ins_name;
        this.ins_recommend = ins_recommend;
        this.ins_description = ins_description;
        this.ins_date = ins_date;
    }

    /*Get和Set方法*/

    public String getIns_date() {
        return ins_date;
    }

    public void setIns_date(String ins_date) {
        this.ins_date = ins_date;
    }

    public int getIns_id() {
        return ins_id;
    }

    public void setIns_id(int ins_id) {
        this.ins_id = ins_id;
    }

    public String getIns_name() {
        return ins_name;
    }

    public void setIns_name(String ins_name) {
        this.ins_name = ins_name;
    }

    public String getIns_recommend() {
        return ins_recommend;
    }

    public void setIns_recommend(String ins_recommend) {
        this.ins_recommend = ins_recommend;
    }

    public String getIns_description() {
        return ins_description;
    }

    public void setIns_description(String ins_description) {
        this.ins_description = ins_description;
    }
    /*toString方法*/

    @Override
    public String toString() {
        return "Institution{" +
                "ins_id=" + ins_id +
                ", ins_name='" + ins_name + '\'' +
                ", ins_recommend='" + ins_recommend + '\'' +
                ", ins_description='" + ins_description + '\'' +
                ", ins_date='" + ins_date + '\'' +
                '}';
    }
}
