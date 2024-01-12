package lk.ijse.finalwithlayered.dao.interfaces;

import lk.ijse.finalwithlayered.dao.CrudUtil;
import lk.ijse.finalwithlayered.dao.SuperDAO;

import java.sql.SQLException;

public interface UserDAO extends SuperDAO {
    String getUserId() throws SQLException, ClassNotFoundException;

}
