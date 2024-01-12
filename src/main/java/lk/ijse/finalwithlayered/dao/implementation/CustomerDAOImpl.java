package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.CustomerDAO;
import lk.ijse.finalwithlayered.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst=SqlUtil.execute("SELECT * FROM customer");

        ArrayList<Customer> allCustomers = new ArrayList<>();

        while (rst.next()){
            Customer entity = new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
            allCustomers.add(entity);
        }

        return allCustomers;
    }

    @Override
    public boolean save(Customer dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Customer dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
       return SqlUtil.execute("DELETE from customer where custId=?",id);
    }

    @Override
    public ArrayList<String> getAllId()  throws SQLException, ClassNotFoundException {
       ResultSet rst=SqlUtil.execute("SELECT custId from customer");

        ArrayList<String> custIds = new ArrayList<>();

        while (rst.next()){
            String ids = rst.getString(1);
            custIds.add(ids);
        }

        return custIds;
    }

    @Override
    public Customer get(String updateEmpId) throws SQLException, ClassNotFoundException {
        ResultSet rst=SqlUtil.execute("SELECT * from customer where custId=?",updateEmpId);

        Customer entity = null;
        if(rst.next()){
           entity = new Customer(
                    updateEmpId + "",
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4)
            );
        }
        return entity;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
