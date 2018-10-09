package yqius.dataDeal.util.dboperation;

import org.apache.commons.beanutils.BeanUtils;
import yqius.dataDeal.util.ConnectionPool;
import yqius.dataDeal.util.ConnectionPoolManager;
import yqius.dataDeal.util.StrUtil;
import yqius.dataDeal.util.dboperation.inter.SelectInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
}
