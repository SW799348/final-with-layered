package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;
import lk.ijse.finalwithlayered.dto.EmployeeDto;
import lk.ijse.finalwithlayered.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    boolean deleteEmployee(String id) throws SQLException, ClassNotFoundException;

    ArrayList<EmployeeDto> getAllEmployees() throws SQLException, ClassNotFoundException;

    Employee getEmployee(String updateEmpId) throws SQLException, ClassNotFoundException;

    ArrayList<String> getAllEmpId() throws SQLException, ClassNotFoundException;
}
