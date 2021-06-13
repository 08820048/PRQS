package cn.hrbcu.com.dao.impl;

import cn.hrbcu.com.dao.AdminDAO;
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
 * @author: XuYi
 * @date: 2021/5/23 14:56
 * @description:
 */
public class AdminDAOImpl implements AdminDAO {

    /*创建一个JDBCTemplate连接池，用作数据库连接*/
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public void delete(int id) {
        /*编写sql语句*/
        String sql = "delete from institution where ins_id=?";
        /*执行操作*/
        template.update(sql,id);
    }

    /**
     * 接口方法重写实现
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
     * 接口方法重写实现
     * @param condition 条件
     * @return
     */
    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        String sql = "select count(*) from institution where 1 = 1 ";
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

    /**
     * 接口方法重写实现
     * @param start 起始页码
     * @param rows 行数
     * @param condition 条件
     * @return
     */
    @Override
    public List<Institution> findByPage(int start, int rows, Map<String, String[]> condition) {
        String sql = "select * from institution where 1 = 1 ";
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
        return template.query(sql,new BeanPropertyRowMapper<Institution>(Institution.class),params.toArray());
    }



    @Override
    public void update(Institution institution) {
        /*编写sql语句*/
        String sql = "update institution set ins_name=?,ins_recommend=?,ins_description=? where ins_id = ?";
        /*执行更新操作*/
        template.update(sql,
                institution.getIns_name(),
                institution.getIns_recommend(),
                institution.getIns_description(),
                institution.getIns_id()
                );
    }

    @Override
    public Institution findById(int id) {
        /*编写sql语句*/
        String sql = "select * from institution where ins_id=?";
        /*执行语句查询并返回结果*/
        return template.queryForObject(sql, new BeanPropertyRowMapper<Institution>(Institution.class),id);
    }



    @Override
    public void addIns(Institution institution) {
        /*编写sql语句*/
        String sql = "insert into institution(ins_name,ins_recommend,ins_description) values(?,?,?) ";
        /*语句执行*/
        template.update(sql,
                institution.getIns_name(),
                institution.getIns_recommend(),
                institution.getIns_description()
                );
    }


}
