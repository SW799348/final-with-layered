package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.UpdateCustomerDAO;
import lk.ijse.finalwithlayered.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateCustomerDAOImpl implements UpdateCustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE customer SET custName=?,address=?,tel=? WHERE custId=?;",
                entity.getName(),
                entity.getAddress(),
                entity.getTel(),
                entity.getCustId());
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
    public Customer get(String updateEmpId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
