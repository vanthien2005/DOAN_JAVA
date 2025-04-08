package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class duLieu {
   public duLieu(){
    }
    public static Connection ket_noi(){
        Connection conn = null;
        String url = "jdbc:sqlserver://localhost:1433;databaseName=RCP;encrypt=false";
        String user = "sa";
        String password = "22082005";
        try {
            // Kết nối đến SQL Server
             conn = DriverManager.getConnection(url, user, password);
    
        } catch (SQLException e) {
            System.out.println("loi ket noi: " + e.getMessage());
        }
        return conn;
    }

}
