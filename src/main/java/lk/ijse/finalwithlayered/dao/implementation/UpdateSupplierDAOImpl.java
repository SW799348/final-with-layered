package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.UpdateSupplierDAO;
import lk.ijse.finalwithlayered.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateSupplierDAOImpl implements UpdateSupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Supplier dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE supplier SET name=?,contact_info=?,supplier_address=? WHERE supplierId=?;",
                entity.getName(),
                entity.getContact_info(),
                entity.getAddress(),
                entity.getSupplierId()

        );
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
    public Supplier get(String updateEmpId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
