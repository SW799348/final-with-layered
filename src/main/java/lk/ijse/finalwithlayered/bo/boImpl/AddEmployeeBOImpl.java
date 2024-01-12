package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.boInter.AddEmployeeBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.interfaces.AddEmployeeDAO;
import lk.ijse.finalwithlayered.dto.EmployeeDto;
import lk.ijse.finalwithlayered.entity.Employee;

import java.sql.SQLException;

public class AddEmployeeBOImpl implements AddEmployeeBO {

    AddEmployeeDAO addEmployeeDAO= (AddEmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ADD_EMPLOYEE);
    @Override
    public String generateNextEmployeeId() throws SQLException, ClassNotFoundException {
        return addEmployeeDAO.generateNewId();
    }

    @Override
    public boolean saveEmployee(EmployeeDto employeeDto) throws SQLException, ClassNotFoundException {
        return addEmployeeDAO.save(new Employee(employeeDto.getEmpId(),employeeDto.getName(),employeeDto.getAddress(),employeeDto.getTel(),employeeDto.getUserId()));
    }
}
