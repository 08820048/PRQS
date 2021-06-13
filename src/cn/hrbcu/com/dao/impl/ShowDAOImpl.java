package cn.hrbcu.com.dao.impl;

import cn.hrbcu.com.dao.ShowDAO;
import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.CodeType;
import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.entity.Software;
import cn.hrbcu.com.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author: XuYi
 * @date: 2021/5/22 20:10
 * @description: 首页数据展示模型层接口实现
 */
public class ShowDAOImpl implements ShowDAO {
    /*创建一个JDBCTemplate连接池，用作数据库连接*/
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 查询全部培训机构数据方法实现
     * @return
     */
    @Override
    public List<Institution> selectAllByIns() {
        /*编写sql语句*/
        String sql = "select * from institution";
        /*执行查询*/
        List<Institution> institution = template.query(sql, new BeanPropertyRowMapper<Institution>(Institution.class));
        /*返回结果*/
        return institution;
    }

    /**
     * 查询全部书籍数据方法实现
     * @return
     */
    @Override
    public List<Books> selectAllByBooks() {
        /*编写sql语句*/
        String sql = "select * from books";
        List<Books> books = template.query(sql,new BeanPropertyRowMapper<>(Books.class));
        /*返回结果*/
        return books;
    }

    /**
     * 查询全部软件推荐数据方法实现
     * @return
     */
    @Override
    public List<Software> selectAllBySoft() {
        /*编写sql语句*/
        String sql = "select * from software";
        List<Software> soft = template.query(sql,new BeanPropertyRowMapper<>(Software.class));
        /*返回结果*/
        return soft;
    }

    @Override
    public List<CodeType> selectAllByCode() {
        /*编写sql语句*/
        String sql = "select * from codetype";
        List<CodeType> code = template.query(sql,new BeanPropertyRowMapper<>(CodeType.class));
        /*返回结果*/
        return code;
    }
}
