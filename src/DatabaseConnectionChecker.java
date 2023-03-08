import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionChecker {
    private static final String DATABASE_URL = "jdbc:jtds:sqlserver://DESKTOP-LT1PDRG:1433/Steamv2;instance=SQLEXPRESS;sqlDialect=22";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "user123";

    public static boolean checkConnection() {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}