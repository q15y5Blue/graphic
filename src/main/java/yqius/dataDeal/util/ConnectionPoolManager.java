package yqius.dataDeal.util;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

import yqius.dataDeal.entity.Type;
import yqius.dataDeal.util.inter.selectInterface;

public class ConnectionPoolManager implements selectInterface{


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
        System.out.println("select A object");
        ConnectionPool pool = ConnectionPoolManager.getPool("CMServer");
        PreparedStatement prs =null;
        ResultSet rs = null;
        Connection conn = null;
        try{
            conn = pool.getConnection();
            prs = conn.prepareStatement(sql);
            rs = prs.executeQuery();
            int rowNumber = 1;
            while(rs.next()){
                Type type = new Type();
                type.setTypeName(StrUtil.trimStr(rs.getString("xtype")));
                type.setTypeName(StrUtil.trimStr(rs.getString("b_name")));
                type.setCount(Integer.parseInt(StrUtil.trimStr(rs.getString("count"))));
                rs.getString("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            pool.closePreparedStatement(prs);
            pool.closeResultSet(rs);
            pool.returnConnection(conn);
        }
        return null;
    }

    @Override
    public Collection selectArray(String sql) {
        return null;
    }

    public static void main(String[] args) {
        ConnectionPoolManager cpm = new ConnectionPoolManager();
        cpm.selectObeject("select xtype,b_name,count(*) as count from invoice2018 A, YB_BNAMES B " +
                " where A.xtype is not null AND A.req_no=B.SERIAL_NO " +
                " and rownum  <100 "+
                " group by xtype,b_name order by xtype,b_name");
    }
}
