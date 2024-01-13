package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;
import lk.ijse.finalwithlayered.dto.PlaceOrderDto;
import lk.ijse.finalwithlayered.entity.Customer;
import lk.ijse.finalwithlayered.entity.Material;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO {

    String generateNextOrderId()throws SQLException,ClassNotFoundException;
    ArrayList<String> getAllCustomerIds() throws SQLException, ClassNotFoundException;

    boolean placeOrder(PlaceOrderDto placeOrderDto) throws SQLException,ClassNotFoundException;

    Customer getCustomer(String updateCustId) throws SQLException, ClassNotFoundException;

    Material getMaterial(String updateMaterial) throws SQLException,ClassNotFoundException;

    ArrayList<String> getAllMaterialIds()throws SQLException,ClassNotFoundException;;


//
//
//    boolean saveOrder(String orderId, LocalDate date, String cusId, String status)throws SQLException,ClassNotFoundException;
//
//    boolean saveOrderDetail(String orderId, List<CartTm> tmList) throws SQLException,ClassNotFoundException;
//
//    boolean saveOrderDetail(String orderId, CartTm cartTm) throws SQLException,ClassNotFoundException;


}
