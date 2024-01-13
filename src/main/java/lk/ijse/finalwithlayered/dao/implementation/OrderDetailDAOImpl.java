package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.OrderDetailDAO;
import lk.ijse.finalwithlayered.entity.CartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<CartTm> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(CartTm dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(CartTm dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public CartTm get(String updateEmpId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean saveOrderDetail(String orderId, CartTm cartTm) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT INTO order_detail VALUES(?, ?, ?, ?)",
                cartTm.getMaterialId(),
                orderId,
                cartTm.getUnitPrice(),
                cartTm.getRequiredMaterialQty()
                );
    }

    @Override
    public boolean saveOrderDetail(String orderId, List<CartTm> tmList) throws SQLException, ClassNotFoundException {
        for (CartTm cartTm : tmList) {
            if(!saveOrderDetail(orderId, cartTm)) {
                return false;
            }
        }
        return true;
    }
}
