package lk.ijse.finalwithlayered.dao;

import lk.ijse.finalwithlayered.dbConnection.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionUtil {


    static Connection connection;

    static {
        try {
            connection = DbConnection.getDbConnection().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public TransactionUtil() throws SQLException, ClassNotFoundException {
    }


    public static void startTransaction() throws SQLException {
        connection.setAutoCommit(false);
    }

    public static void rollbackTransaction() throws SQLException {
        connection.rollback();
        connection.setAutoCommit(true);
    }

    public static void endTransaction() throws SQLException {
        connection.commit();
        connection.setAutoCommit(true);
    }
}
