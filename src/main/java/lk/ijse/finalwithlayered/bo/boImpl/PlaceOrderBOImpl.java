package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.boInter.PlaceOrderBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.TransactionUtil;
import lk.ijse.finalwithlayered.dao.interfaces.CustomerDAO;
import lk.ijse.finalwithlayered.dao.interfaces.OrderDAO;
import lk.ijse.finalwithlayered.dao.interfaces.OrderDetailDAO;
import lk.ijse.finalwithlayered.dao.interfaces.RawMaterialDAO;
import lk.ijse.finalwithlayered.dto.PlaceOrderDto;
import lk.ijse.finalwithlayered.entity.CartTm;
import lk.ijse.finalwithlayered.entity.Customer;
import lk.ijse.finalwithlayered.entity.Material;
import lk.ijse.finalwithlayered.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderBOImpl implements PlaceOrderBO {

    CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    RawMaterialDAO rawMaterialDAO= (RawMaterialDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RAW_MATERIAL);

    OrderDAO orderDAO= (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    OrderDetailDAO orderDetailDAO= (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);


    @Override
    public String generateNextOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewId();
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

    @Override
    public boolean placeOrder(PlaceOrderDto placeOrderDto) throws SQLException, ClassNotFoundException {
        TransactionUtil.startTransaction();

        boolean isOrderSaved = false;

            isOrderSaved = orderDAO.save(new Order(placeOrderDto.getOrderId(), placeOrderDto.getDate(), placeOrderDto.getCusId(), placeOrderDto.getStatus()));

            if (!isOrderSaved) {
                TransactionUtil.rollbackTransaction();
                return false;
            }
            boolean isUpdated = rawMaterialDAO.updateRawMaterial(placeOrderDto.getTmList());

            if (!isUpdated) {
                TransactionUtil.rollbackTransaction();
                return false;
            }

            boolean isOrderDetailSaved = orderDetailDAO.saveOrderDetail(placeOrderDto.getOrderId(), (CartTm) placeOrderDto.getTmList());
            if (!isOrderDetailSaved) {
                TransactionUtil.rollbackTransaction();
                return false;
            }
        TransactionUtil.endTransaction();
        return true;
        }


    @Override
    public Customer getCustomer(String updateCustId) throws SQLException, ClassNotFoundException {
        return customerDAO.get(updateCustId);
    }

    @Override
    public Material getMaterial(String updateMaterial) throws SQLException, ClassNotFoundException {
        return rawMaterialDAO.get(updateMaterial);
    }

    @Override
    public ArrayList<String> getAllMaterialIds() throws SQLException, ClassNotFoundException {
        ArrayList<Material> materials = rawMaterialDAO.getAll();

        // Step 2: Create a new list to store customer IDs
        ArrayList<String> materialIds = new ArrayList<>();

        // Step 3: Iterate through each Customer object in the retrieved list
        for (Material material : materials){
            // Step 4: Extract the customer ID and add it to the list
            String matId = material.getMaterialId();
            materialIds.add(matId);
        }

        // Step 5: Return the list of customer IDs
        return materialIds;
    }
}
