package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.UserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {
    @Override
    public String getUserId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.execute("select userId from user");

        String id =null;

        if(resultSet.next()){
            id = resultSet.getString(5);
        }
        return id;


    }
}
