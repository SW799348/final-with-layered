package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.boInter.EmployeeBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.interfaces.EmployeeDAO;
import lk.ijse.finalwithlayered.dto.EmployeeDto;
import lk.ijse.finalwithlayered.entity.Customer;
import lk.ijse.finalwithlayered.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO= (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(id);
    }

    @Override
    public ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();

        for (Employee employee : employees){
            employeeDtos.add(new EmployeeDto(employee.getEmpId(),employee.getName(),employee.getAddress(),employee.getTel(),employee.getUserId()));
        }
        return employeeDtos;
    }

    @Override
    public Employee getEmployee(String updateEmpId) throws SQLException, ClassNotFoundException {
        return employeeDAO.get(updateEmpId);
    }

    @Override
    public ArrayList<String> getAllEmpId() throws SQLException, ClassNotFoundException {

        ArrayList<Employee> employees = employeeDAO.getAll();

        ArrayList<String> empIds = new ArrayList<>();

        for (Employee employee : employees){
            String empId = employee.getEmpId();
            empIds.add(empId);
        }

        return empIds;
    }
}
