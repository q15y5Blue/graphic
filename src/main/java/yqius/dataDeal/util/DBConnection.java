package yqius.dataDeal.util;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DBConnection {

     protected static DataSource getDatasource() {
        PoolProperties p = new PoolProperties();
        p.setUrl("192.168.15.94:1521/orcll");
        p.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        p.setUsername("fdall");
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
        DataSource datasource = new DataSource();
        datasource.setPoolProperties(p);
        return datasource;
    }

    public static void main(String[] args) {
        DBConnection db = new DBConnection();
        System.out.println(db.getDatasource());
    }
}
