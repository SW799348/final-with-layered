package lk.ijse.finalwithlayered.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;
    private final Connection connection;

    private DbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/factory10", "root", "Ijse@1234");
    }

    public static DbConnection getDbConnection() throws SQLException, ClassNotFoundException {
        return dbConnection == null ? dbConnection= new DbConnection() : dbConnection;
    }

    public static DbConnection getInstance() {
        return null;
    }

    public Connection getConnection() {
        return connection;
    }
}
