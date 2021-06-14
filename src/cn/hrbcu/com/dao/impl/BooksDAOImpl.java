package cn.hrbcu.com.dao.impl;

import cn.hrbcu.com.dao.BooksDAO;
import cn.hrbcu.com.entity.Books;
import cn.hrbcu.com.entity.Institution;
import cn.hrbcu.com.utils.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @date: 2021/5/26 18:07
 * @description:
 */
public class BooksDAOImpl implements BooksDAO {
    /*创建一个JDBCTemplate连接池，用作数据库连接*/
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());


    @Override
    public List<Books> selectAllByBooks() {
        /*编写sql语句*/
        String sql = "select * from books";
        /*执行查询*/
        List<Books> books = template.query(sql, new BeanPropertyRowMapper<Books>(Books.class));
        /*返回结果*/
        return books;
    }

    @Override
    public List<Books> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from books where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> keySet = condition.keySet();
        //定义一个参数集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }
        //添加分页的查询
        sb.append(" limit ?,? ");
        //添加分页查询参数值
        params.add(start);
        params.add(rows);
        sql = sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<Books>(Books.class),params.toArray());
    }

    @Override
    public void update(Books books) {
        /*编写sql语句*/
        String sql = "update books set books_name=?,books_recommend=?,books_description=? where books_id = ?";
        /*执行更新操作*/
        template.update(sql,
                books.getBooks_name(),
                books.getBooks_recommend(),
                books.getBooks_description(),
                books.getBooks_id()
        );
    }

    @Override
    public Books findById(int id) {
        /*编写sql语句*/
        String sql = "select * from books where books_id=?";
        /*执行语句查询并返回结果*/
        return template.queryForObject(sql, new BeanPropertyRowMapper<Books>(Books.class),id);
    }

    @Override
    public void addBooks(Books books) {
        /*编写sql语句*/
        String sql = "insert into books(books_name,books_recommend,books_description) values(?,?,?) ";
        /*语句执行*/
        template.update(sql,
                books.getBooks_name(),
                books.getBooks_recommend(),
                books.getBooks_description()
        );
    }

    @Override
    public void delete(int id) {
        /*编写sql语句*/
        String sql = "delete from books where books_id=?";
        /*执行操作*/
        template.update(sql,id);
    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from books where 1 = 1 ";
        StringBuilder sb = new StringBuilder(sql);
        //遍历map
        Set<String> keySet = condition.keySet();
        //定义一个参数集合
        List<Object> params = new ArrayList<Object>();
        for (String key : keySet) {
            //排除分页条件参数
            if ("currentPage".equals(key) || "rows".equals(key)){
                continue;
            }
            //获取value
            String value = condition.get(key)[0];
            //判断value是否有值
            if (value!=null && !"".equals(value)){
                sb.append(" and "+key+" like ? ");
                params.add("%"+value+"%");//？条件的值
            }
        }
        System.out.println(sb.toString());
        System.out.println(params);
        return template.queryForObject(sb.toString(),Integer.class,params.toArray());
    }

}
