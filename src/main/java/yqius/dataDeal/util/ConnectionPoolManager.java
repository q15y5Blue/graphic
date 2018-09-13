package yqius.dataDeal.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.apache.commons.beanutils.BeanUtils;
import yqius.dataDeal.util.inter.SelectInterface;

public class ConnectionPoolManager implements SelectInterface{


    /** m
     * 虚假的接口
     * 为了配合现有系统
     * @param str
     * @return
     */
    public static ConnectionPool getPool(String str){
        return ConnectionPool.getInstance();
    }

    @Override
    public Object selectObeject(String sql) {
        return null;
    }

    @Override
    public List selectArray(String sql, Class clazz) {
//        System.out.println("select A object");
        ConnectionPool pool = ConnectionPoolManager.getPool("CMServer");//
        PreparedStatement prs =null;
        ResultSet rs = null;
        Connection conn = null;
        try{
            conn = pool.getConnection();
            prs = conn.prepareStatement(sql);
            rs = prs.executeQuery();
            int rowNumber = 1;
            List<Object> list = new ArrayList<Object>();
            int col = rs.getMetaData().getColumnCount();
            while(rs.next()){
                Object obj = clazz.getDeclaredConstructor().newInstance();
                for(int i=1;i<=col;i++){
                    BeanUtils.setProperty(obj,rs.getMetaData().getColumnName(i).toLowerCase(),StrUtil.trimStr(rs.getString(i)));
                }
                list.add(obj);
                rowNumber ++;//test
                if(rowNumber >100) break;//test
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
