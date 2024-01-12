package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;
import lk.ijse.finalwithlayered.dto.CustomerDto;

import java.sql.SQLException;

public interface UpdateCustomerBO extends SuperBO {
    boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;

}
