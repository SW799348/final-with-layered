package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.AddSupplierDAO;
import lk.ijse.finalwithlayered.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddSupplierDAOImpl implements AddSupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("insert into supplier values (?,?,?,?)",
                entity.getSupplierId(),
                entity.getName(),
                entity.getContact_info(),
                entity.getAddress()
             );
    }

    @Override
    public boolean update(Supplier dto) throws SQLException, ClassNotFoundException {
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
    public Supplier get(String updateEmpId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT supplierId FROM supplier");

        int max = 0;
        while (resultSet.next()){
            String x = resultSet.getString(1);
            String[] y = x.split("S");
            int id = Integer.parseInt(y[1]);

            if (max < id){
                max = id;
            }

        }

        return "S00" + ++max;

    }
}
