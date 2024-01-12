package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;
import lk.ijse.finalwithlayered.dto.CustomerDto;

import java.sql.SQLException;

public interface AddCustomerBO extends SuperBO {

    String generateNextCustId()throws SQLException, ClassNotFoundException;
    boolean saveCustomer(CustomerDto dto)throws SQLException, ClassNotFoundException;

}
