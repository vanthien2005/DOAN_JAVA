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
        String password = "123456789";
        try {
            // Kết nối đến SQL Server
             conn = DriverManager.getConnection(url, user, password);
    
        } catch (SQLException e) {
            System.out.println("loi ket noi: " + e.getMessage());
        }
        return conn;
    }
    public static void close(){
       try(Connection con= ket_noi()) {
       con.close();
        
       } catch (Exception e) {
        System.out.println(e.getMessage());
       }
    }

}
