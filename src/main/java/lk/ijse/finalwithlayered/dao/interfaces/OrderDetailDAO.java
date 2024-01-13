package lk.ijse.finalwithlayered.dao.interfaces;

import lk.ijse.finalwithlayered.dao.CrudUtil;
import lk.ijse.finalwithlayered.entity.CartTm;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudUtil<CartTm> {

    boolean saveOrderDetail(String orderId, CartTm cartTm) throws SQLException,ClassNotFoundException;

    boolean saveOrderDetail(String orderId, List<CartTm> tmList) throws SQLException,ClassNotFoundException;
}
