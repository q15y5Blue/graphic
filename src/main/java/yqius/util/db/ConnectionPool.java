package yqius.util.db;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
//import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
//import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionPool  {

    private static volatile ConnectionPool instance = null;

    public ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    public static DataSource getDatasource(){
//        DataSource datasource = new DataSource();
//        datasource.setPoolProperties(PoolProperties());
//        return datasource;
        DataSource ds =null;
        try {
            Context context = new InitialContext();
            ds =(DataSource)context.lookup("java:comp/env/jdbc/wf_tiy");
            return ds;
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ds;
    }

    /**
     * 这种方法会导致重复连接数据库
     * @return
     */
     private static PoolProperties PoolProperties() {
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:oracle:thin:@192.168.102.5:1521/orcl");
        p.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        p.setUsername("shoptest_nyd");
        p.setPassword("zhao");
        p.setJmxEnabled(true);
        p.setTestWhileIdle(false);
        p.setTestOnBorrow(true);
        p.setValidationQuery("SELECT 1");
        p.setTestOnReturn(false);
        p.setValidationInterval(30000);
        p.setTimeBetweenEvictionRunsMillis(30000);
        p.setMaxActive(100);
        p.setInitialSize(10);
        p.setMaxWait(10000);
        p.setRemoveAbandonedTimeout(60);
        p.setMinEvictableIdleTimeMillis(30000);
        p.setMinIdle(10);
        p.setLogAbandoned(true);
        p.setRemoveAbandoned(true);
        p.setJdbcInterceptors(
                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
        return p;
    }


    public static Connection getConnection(){
        Connection con=null;
        try{
            DataSource tp = getDatasource();
            con = tp.getConnection();

        }catch (SQLException sqe){
            sqe.printStackTrace();
        }
        return  con;
    }

    public static void closePreparedStatement(PreparedStatement ps){
        try {
            if (ps!=null)
                ps.close();
        }catch (SQLException sqe){
            sqe.printStackTrace();
        }
    }

    public static void closeResultSet(ResultSet rs){
        try{
            if(rs!=null)
                rs.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void returnConnection(Connection con){
        try {
            if (con!=null||!con.isClosed())
                con.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * JNDI数据库连接池
     * 这个方法需要把context文件加入到tomcat运行环境下
     */
    public static javax.sql.DataSource JNDIDataSource(){
        javax.sql.DataSource ds =null;
        try {
            Context context = new InitialContext();
            ds =(javax.sql.DataSource)context.lookup("java:comp/env/jdbc/wf_tiy");
            return ds;
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return ds;
    }


}
