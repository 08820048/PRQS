package cn.hrbcu.com.entity;

import java.util.List;

/**
 * @author: XuYi
 * @date: 2021/5/23 16:42
 * @description: 用于实现分页查询实体类
 */
public class Page<T> {
    /*总记录数*/
    private int totalCount;
    /*总页数*/
    private int totalPage;
    /*每页的数据*/
    private List<T> list;
    /*当前的页码*/
    private int currentPage;
    /*每页显示的记录*/
    private int rows;

    /*构造方法*/
    public Page(){};

    public Page(int totalCount, int totalPage, List<T> list, int currentPage, int rows) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
        this.currentPage = currentPage;
        this.rows = rows;
    }

    /*Get和Set*/

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
    /*toString方法*/

    @Override
    public String toString() {
        return "Page{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}
