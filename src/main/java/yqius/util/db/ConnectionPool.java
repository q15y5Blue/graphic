package yqius.util.db;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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

    private static DataSource getDatasource(){
        DataSource da=null;
        try {
            Context ctx = new InitialContext();
            da = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql_connect ");
            return da;
        } catch (NamingException e) {
            e.printStackTrace();
        }
        return da;
    }

//     private static DataSource getDatasource() {
//        PoolProperties p = new PoolProperties();
////        p.setUrl("jdbc:oracle:thin:@192.168.15.94:1521/orcll");
//        p.setUrl("jdbc:oracle:thin:@192.168.102.5:1521/orcl");
//        p.setDriverClassName("oracle.jdbc.driver.OracleDriver");
////        p.setUsername("fdall");
////        p.setPassword("zhao");
//        p.setUsername("shoptest_nyd");
//        p.setPassword("zhao");
//        p.setJmxEnabled(true);
//        p.setTestWhileIdle(false);
//        p.setTestOnBorrow(true);
//        p.setValidationQuery("SELECT 1");
//        p.setTestOnReturn(false);
//        p.setValidationInterval(30000);
//        p.setTimeBetweenEvictionRunsMillis(30000);
//        p.setMaxActive(100);
//        p.setInitialSize(10);
//        p.setMaxWait(10000);
//        p.setRemoveAbandonedTimeout(60);
//        p.setMinEvictableIdleTimeMillis(30000);
//        p.setMinIdle(10);
//        p.setLogAbandoned(true);
//        p.setRemoveAbandoned(true);
//        p.setJdbcInterceptors(
//                "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
//                        "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
//        DataSource datasource = new DataSource();
//        datasource.setPoolProperties(p);
//        return datasource;
//    }


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

        }
}

}
