package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.boInter.CustomerBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.interfaces.CustomerDAO;
import lk.ijse.finalwithlayered.dto.CustomerDto;
import lk.ijse.finalwithlayered.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);


    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException, ClassNotFoundException {

        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDto> customerDTOS = new ArrayList<>();

        for (Customer customer : customers){
            customerDTOS.add(new CustomerDto(customer.getCustId(),customer.getName(),customer.getAddress(),customer.getTel()));
        }
        return customerDTOS;
    }

    @Override
    public Customer getCustomer(String updateCustId) throws SQLException, ClassNotFoundException {
        return customerDAO.get(updateCustId);
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = customerDAO.getAll();

        // Step 2: Create a new list to store customer IDs
        ArrayList<String> customerIds = new ArrayList<>();

        // Step 3: Iterate through each Customer object in the retrieved list
        for (Customer customer : customers){
            // Step 4: Extract the customer ID and add it to the list
            String customerId = customer.getCustId();
            customerIds.add(customerId);
        }

        // Step 5: Return the list of customer IDs
        return customerIds;
    }


}
