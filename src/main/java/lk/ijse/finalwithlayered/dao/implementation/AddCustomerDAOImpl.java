package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.AddCustomerDAO;
import lk.ijse.finalwithlayered.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddCustomerDAOImpl implements AddCustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("insert into customer values (?,?,?,?)",
                entity.getCustId(),
                entity.getName(),
                entity.getAddress(),
                entity.getTel());
    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
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
    public Customer get(String updateEmpId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT custId FROM customer");

        int max = 0;
        while (resultSet.next()){
            String x = resultSet.getString(1);
            String[] y = x.split("C");
            int id = Integer.parseInt(y[1]);

            if (max < id){
                max = id;
            }

        }

        return "C00" + ++max;



    }


}
