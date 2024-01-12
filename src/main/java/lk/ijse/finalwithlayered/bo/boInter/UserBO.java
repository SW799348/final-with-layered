package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;

import java.sql.SQLException;

public interface UserBO extends SuperBO {
    String getUserId() throws SQLException, ClassNotFoundException;
}
