package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.SalaryDAO;
import lk.ijse.finalwithlayered.entity.Material;
import lk.ijse.finalwithlayered.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public ArrayList<Salary> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SqlUtil.execute("SELECT * FROM salary");

        ArrayList<Salary> allSalaries = new ArrayList<>();

        while (rst.next()) {
            Salary entity = new Salary(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5),
                    rst.getInt(6)
            );
            allSalaries.add(entity);
        }
        return allSalaries;
    }

    @Override
    public boolean save(Salary entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT into salary values (?,?,?,?,?,?);",
                entity.getSalaryId(),
                entity.getEmpId(),
                entity.getMonth(),
                entity.getOt_hours(),
                entity.getTotal_salary(),
                entity.getAmount_of_work_done_per_month()

                );
    }

    @Override
    public boolean update(Salary dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("DELETE  from salary where salaryId=?;",id);

    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Salary get(String updateEmpId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT salaryId FROM salary");

        int max = 0;
        while (resultSet.next()){
            String x = resultSet.getString(1);
            String[] y = x.split("Sa");
            int id = Integer.parseInt(y[1]);

            if (max < id){
                max = id;
            }

        }

        return "Sa00" + ++max;
    }
}
