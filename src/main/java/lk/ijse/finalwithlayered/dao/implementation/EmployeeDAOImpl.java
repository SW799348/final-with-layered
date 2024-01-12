package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.EmployeeDAO;
import lk.ijse.finalwithlayered.entity.Customer;
import lk.ijse.finalwithlayered.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rst= SqlUtil.execute("SELECT * FROM employee");

        ArrayList<Employee> allEmployees = new ArrayList<>();

        while (rst.next()){
            Employee entity = new Employee(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5)
            );
            allEmployees.add(entity);
        }

        return allEmployees;
    }

    @Override
    public boolean save(Employee dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Employee dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE from employee where empId=?",id);

    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet rst=SqlUtil.execute("SELECT empId from employee");

        ArrayList<String> empIds = new ArrayList<>();

        while (rst.next()){
            String ids = rst.getString(1);
            empIds.add(ids);
        }

        return empIds;
    }

    @Override
    public Employee get(String updateEmpId) throws SQLException, ClassNotFoundException {
        ResultSet rst=SqlUtil.execute("SELECT * from employee where empId=?",updateEmpId);

        Employee entity = null;
        if(rst.next()){
            entity = new Employee(
                    updateEmpId + "",
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getString(5)
            );
        }
        return entity;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }
}
