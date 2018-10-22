package yqius.util.db;

//import org.apache.tomcat.jdbc.pool.ConnectionPool;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import yqius.core.StartMode;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolManager {

    /**
     * 虚假的接口
     * 为了配合现有系统
     * @param 'str
     * @return
     */
    public static ConnectionPool getPool(String str){
        //这里可以根据不同的Str后续来判断数据库连接
        return ConnectionPool.getInstance();
    }

}
