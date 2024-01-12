package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;
import lk.ijse.finalwithlayered.dto.EmployeeDto;

import java.sql.SQLException;

public interface AddEmployeeBO extends SuperBO {

    String generateNextEmployeeId()throws SQLException, ClassNotFoundException;

    boolean saveEmployee(EmployeeDto employeeDto)throws SQLException, ClassNotFoundException;
}
