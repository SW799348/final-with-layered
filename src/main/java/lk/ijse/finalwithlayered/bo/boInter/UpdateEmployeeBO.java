package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;
import lk.ijse.finalwithlayered.dto.EmployeeDto;

import java.sql.SQLException;

public interface UpdateEmployeeBO extends SuperBO {

    boolean updateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException;



}
