package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.UpdateEmployeeDAO;
import lk.ijse.finalwithlayered.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class UpdateEmployeeDAOImpl implements UpdateEmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Employee dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Employee entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE employee SET name=?,address=?,tel=?,userId=? WHERE empId=?;",
                entity.getName(),
                entity.getAddress(),
                entity.getTel(),
                entity.getUserId(),
                entity.getEmpId()
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
    public Employee get(String updateEmpId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
