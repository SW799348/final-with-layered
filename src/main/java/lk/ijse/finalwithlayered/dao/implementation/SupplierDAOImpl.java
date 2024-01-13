package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.SupplierDAO;
import lk.ijse.finalwithlayered.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst= SqlUtil.execute("SELECT * FROM supplier");

        ArrayList<Supplier> allSuppliers = new ArrayList<>();

        while (rst.next()){
            Supplier entity = new Supplier(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
            allSuppliers.add(entity);
        }

        return allSuppliers;
    }

    @Override
    public boolean save(Supplier dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("delete from supplier where supplierId=?;",id);
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Supplier get(String updateEmpId) throws SQLException, ClassNotFoundException {
        ResultSet rst=SqlUtil.execute("select * from supplier where supplierId=?",updateEmpId);

        Supplier entity = null;
        if(rst.next()){
            entity = new Supplier(
                    updateEmpId + "",
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4)
            );
        }
        return entity;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
