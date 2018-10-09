package yqius.dataDeal.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import org.apache.commons.beanutils.BeanUtils;
import yqius.dataDeal.util.dboperation.inter.SelectInterface;

public class ConnectionPoolManager {


    /**
     * 虚假的接口
     * 为了配合现有系统
     * @param str
     * @return
     */
    public static ConnectionPool getPool(String str){
        //这里可以根据不同的Str后续来判断数据库连接
        return ConnectionPool.getInstance();
    }

}
