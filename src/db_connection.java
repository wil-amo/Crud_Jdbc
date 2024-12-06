import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class db_connection {


    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/jdbc_crud";
    private static final String USER = "root";
    private static final String PASSWORD = "00000000";

    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }

}


