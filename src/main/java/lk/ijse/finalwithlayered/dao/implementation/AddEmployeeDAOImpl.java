package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.AddEmployeeDAO;
import lk.ijse.finalwithlayered.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddEmployeeDAOImpl implements AddEmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Employee entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("insert into employee values (?,?,?,?,?)",
                entity.getEmpId(),
                entity.getName(),
                entity.getAddress(),
                entity.getTel(),
                entity.getUserId());
    }

    @Override
    public boolean update(Employee dto) throws SQLException, ClassNotFoundException {
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
    public Employee get(String updateEmpId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT empId FROM employee");

        int max = 0;
        while (resultSet.next()){
            String x = resultSet.getString(1);
            String[] y = x.split("E");
            int id = Integer.parseInt(y[1]);

            if (max < id){
                max = id;
            }

        }

        return "E00" + ++max;

    }
}
