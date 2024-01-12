package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;
import lk.ijse.finalwithlayered.dto.CustomerDto;
import lk.ijse.finalwithlayered.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDto> getAllCustomers()throws SQLException, ClassNotFoundException;
    Customer getCustomer(String updateCustId) throws SQLException, ClassNotFoundException;
    boolean deleteCustomer(String id)  throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;
}
