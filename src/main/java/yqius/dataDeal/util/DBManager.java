package yqius.dataDeal.util;

import java.sql.Connection;
import java.sql.SQLException;

public class DBManager {

    public Connection getConnectionByPool(){
        Connection con=null;
        try{
            con = DBConnection.getDatasource().getConnection();
        }catch (SQLException sqe){
            sqe.printStackTrace();
        }
        return  con;
    }


}
