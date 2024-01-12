package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.boInter.UpdateCustomerBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.interfaces.UpdateCustomerDAO;
import lk.ijse.finalwithlayered.dto.CustomerDto;
import lk.ijse.finalwithlayered.entity.Customer;

import java.sql.SQLException;

public class UpdateCustomerBOImpl implements UpdateCustomerBO {

    UpdateCustomerDAO updateCustomerDAO= (UpdateCustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.UPDATE_CUSTOMER);

    @Override
    public boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return updateCustomerDAO.update(new Customer(customerDto.getCustId(), customerDto.getName(), customerDto.getAddress(),customerDto.getTel()));
    }
}
