package cn.hrbcu.com.entity;

/**
 * @date: 2021/5/21 22:01
 * @description: 书籍推荐实体类
 */
public class Books {
    /*书籍编号*/
    private int books_id;
    /*书籍名称*/
    private String books_name;
    /*推荐指数*/
    private String books_recommend;
    /*书籍概述*/
    private String books_description;
    /*修改时间*/
    private String books_date;
    /*构造方法*/
    public Books() {}

    public Books(int books_id, String books_name, String books_recommend, String books_description,String books_date) {
        this.books_id = books_id;
        this.books_name = books_name;
        this.books_recommend = books_recommend;
        this.books_description = books_description;
        this.books_date = books_date;
    }

    /*Get和Set方法*/

    public String getBooks_date() {
        return books_date;
    }

    public void setBooks_date(String books_date) {
        this.books_date = books_date;
    }

    public int getBooks_id() {
        return books_id;
    }

    public void setBooks_id(int books_id) {
        this.books_id = books_id;
    }

    public String getBooks_name() {
        return books_name;
    }

    public void setBooks_name(String books_name) {
        this.books_name = books_name;
    }

    public String getBooks_recommend() {
        return books_recommend;
    }

    public void setBooks_recommend(String books_recommend) {
        this.books_recommend = books_recommend;
    }

    public String getBooks_description() {
        return books_description;
    }

    public void setBooks_description(String books_description) {
        this.books_description = books_description;
    }

    /*toString方法*/
    @Override
    public String toString() {
        return "Books{" +
                "books_id=" + books_id +
                ", books_name='" + books_name + '\'' +
                ", books_recommend='" + books_recommend + '\'' +
                ", books_description='" + books_description + '\'' +
                ", books_date='" + books_date + '\'' +
                '}';
    }
}
