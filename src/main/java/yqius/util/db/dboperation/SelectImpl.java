package yqius.util.db.dboperation;

import org.apache.commons.beanutils.BeanUtils;
import yqius.util.db.ConnectionPool;
import yqius.util.db.ConnectionPoolManager;
import yqius.dataDeal.util.StrUtil;
import yqius.util.db.dboperation.inter.SelectInterface;
//import org.apache.tomcat.jdbc.pool.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库查询实现
 */
public class SelectImpl implements SelectInterface {

    @Override
    public Object selectObeject(String sql) {
        return null;
    }

    @Override
    public List selectArray(String sql, Class clazz) {
        ConnectionPool pool = ConnectionPoolManager.getPool("CMServer");//
        PreparedStatement prs =null;
        ResultSet rs = null;
        Connection conn = null;
        try{
            conn = pool.getConnection();
            prs = conn.prepareStatement(sql);
            rs = prs.executeQuery();
//            int rowNumber = 1;
            List<Object> list = new ArrayList<Object>();
            int col = rs.getMetaData().getColumnCount();
            while(rs.next()){
                Object obj = clazz.getDeclaredConstructor().newInstance();
                for(int i=1;i<=col;i++){
                    BeanUtils.setProperty(obj,rs.getMetaData().getColumnName(i).toLowerCase(),StrUtil.trimStr(rs.getString(i)));
                }
                list.add(obj);
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pool.closePreparedStatement(prs);
            pool.closeResultSet(rs);
            pool.returnConnection(conn);
        }
        return null;
    }
//    Irene  DELETE * FROM table_name
    @Override
    public void insertData(String sql) {
        ConnectionPool pool = ConnectionPoolManager.getPool("CMServer");//
        PreparedStatement prs = null;
        Connection conn = null;
        try {
            conn = ConnectionPool.getConnection();
            prs = conn.prepareStatement(sql);
            prs.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.closePreparedStatement(prs);
            ConnectionPool.returnConnection(conn);
        }
    }

    @Override
    public void delete(String sql) {
        this.insertData(sql);
    }
}
