package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;
import lk.ijse.finalwithlayered.dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    String generateNextSalaryId() throws SQLException,ClassNotFoundException;

    ArrayList<SalaryDto> getAllSalaries()throws SQLException,ClassNotFoundException;

    boolean deleteSalary(String id)throws SQLException,ClassNotFoundException;

    boolean saveSalary(SalaryDto salaryDto)throws SQLException,ClassNotFoundException;

}
