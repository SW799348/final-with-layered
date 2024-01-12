package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.BOFactory;
import lk.ijse.finalwithlayered.bo.boInter.UserBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.interfaces.UserDAO;

import java.sql.SQLException;

public class UserBOImpl implements UserBO {

    UserDAO userDAO= (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);
    @Override
    public String getUserId() throws SQLException, ClassNotFoundException {
        return userDAO.getUserId();
    }
}
