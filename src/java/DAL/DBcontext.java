package DAL;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBcontext {
    public static Connection getConnection() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlserver://localhost:1433;databaseName=OrganicDB"; 
            String user = "sa"; 
            String password = "12345";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Kết nối thành công!"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
