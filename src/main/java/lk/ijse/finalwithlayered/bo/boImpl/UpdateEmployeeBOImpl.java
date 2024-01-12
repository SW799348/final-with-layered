package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.boInter.UpdateEmployeeBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.interfaces.UpdateEmployeeDAO;
import lk.ijse.finalwithlayered.dto.EmployeeDto;
import lk.ijse.finalwithlayered.entity.Employee;

import java.sql.SQLException;

public class UpdateEmployeeBOImpl implements UpdateEmployeeBO {

    UpdateEmployeeDAO updateEmployeeDAO= (UpdateEmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.UPDATE_EMPLOYEE);
    @Override
    public boolean updateEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return updateEmployeeDAO.update(new Employee(employeeDto.getEmpId(),employeeDto.getName(), employeeDto.getAddress(),employeeDto.getTel(), employeeDto.getUserId()));
    }
}
