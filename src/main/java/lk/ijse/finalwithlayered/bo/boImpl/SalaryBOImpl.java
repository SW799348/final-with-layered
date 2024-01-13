package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.boInter.SalaryBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.interfaces.SalaryDAO;
import lk.ijse.finalwithlayered.dto.MaterialDto;
import lk.ijse.finalwithlayered.dto.SalaryDto;
import lk.ijse.finalwithlayered.entity.Material;
import lk.ijse.finalwithlayered.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {

    SalaryDAO salaryDAO= (SalaryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SALARY);
    @Override
    public String generateNextSalaryId() throws SQLException, ClassNotFoundException {
        return salaryDAO.generateNewId();
    }

    @Override
    public ArrayList<SalaryDto> getAllSalaries() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> salaries = salaryDAO.getAll();
        ArrayList<SalaryDto> salaryDtos = new ArrayList<>();

        for (Salary salary : salaries){
            salaryDtos.add(new SalaryDto(salary.getSalaryId(),salary.getEmpId(),salary.getMonth(),salary.getOt_hours(),salary.getTotal_salary(),salary.getAmount_of_work_done_per_month()));
        }
        return salaryDtos;
    }

    @Override
    public boolean deleteSalary(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(id);
    }

    @Override
    public boolean saveSalary(SalaryDto salaryDto) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(new Salary(salaryDto.getSalaryId(),salaryDto.getEmpId(),salaryDto.getMonth(),salaryDto.getOt_hours(),salaryDto.getTotal_salary(),salaryDto.getAmount_of_work_done_per_month()));
    }
}
