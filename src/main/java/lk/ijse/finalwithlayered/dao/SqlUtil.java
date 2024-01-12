package lk.ijse.finalwithlayered.dao;

import lk.ijse.finalwithlayered.dbConnection.DbConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlUtil {
    public static <T> T execute(String query, Object...args) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = DbConnection.getDbConnection().getConnection().prepareStatement(query);

        for (int i = 0; i < args.length; i++) {
            pstm.setObject((i+1), args[i]);
        }

        if (query.toUpperCase().startsWith("SELECT")){
            return (T) pstm.executeQuery();
        }else
            return (T)(Boolean)(pstm.executeUpdate() > 0);
    }
}
