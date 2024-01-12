package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.boInter.AddCustomerBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.interfaces.AddCustomerDAO;
import lk.ijse.finalwithlayered.dto.CustomerDto;
import lk.ijse.finalwithlayered.entity.Customer;

import java.sql.SQLException;

public class AddCustomerBOImpl implements AddCustomerBO {

    AddCustomerDAO addCustomerDAO= (AddCustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADD_CUSTOMER);
    @Override
    public String generateNextCustId() throws SQLException, ClassNotFoundException {
        return addCustomerDAO.generateNewId();
    }

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException, ClassNotFoundException {
        return addCustomerDAO.save(new Customer(dto.getCustId(),dto.getName(),dto.getAddress(),dto.getTel()));
    }
}
