package lk.ijse.finalwithlayered.dao.implementation;

import lk.ijse.finalwithlayered.dao.SqlUtil;
import lk.ijse.finalwithlayered.dao.interfaces.AttendanceDAO;
import lk.ijse.finalwithlayered.entity.Attendance;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AttendanceDAOImpl implements AttendanceDAO {
    @Override
    public ArrayList<Attendance> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst= SqlUtil.execute("SELECT * from attendance");

        ArrayList<Attendance> allAttendance = new ArrayList<>();

        while (rst.next()){
            Attendance entity = new Attendance(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getDate(3),
                    rst.getTime(4).toLocalTime(),
                    rst.getTime(5).toLocalTime(),
                    rst.getDouble(6)

            );
            allAttendance.add(entity);
        }

        return allAttendance;
    }

    @Override
    public boolean save(Attendance entity) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("INSERT into attendance values (?,?,?,?,?,?);",
                entity.getEmpId(),
                entity.getAttendanceId(),
                entity.getDate(),
                entity.getTime_in(),
                entity.getTime_out(),
                entity.getWorking_hours());
    }

    @Override
    public boolean update(Attendance dto) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("delete from attendance where attendanceId = ?",id);

    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Attendance get(String updateEmpId) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SqlUtil.execute("SELECT attendanceId FROM attendance");

        int max = 0;
        while (resultSet.next()){
            String x = resultSet.getString(1);
            String[] y = x.split("A");
            int id = Integer.parseInt(y[1]);

            if (max < id){
                max = id;
            }

        }

        return "A00" + ++max;
    }

    @Override
    public boolean updateAttendance(String empId, Date date, LocalTime time) throws SQLException, ClassNotFoundException {
        return SqlUtil.execute("UPDATE attendance set time_out=? where empId=? && date=?;",time,empId,date);

    }

    @Override
    public void calculateWorkingHours(String empId, Date date) throws SQLException, ClassNotFoundException {
        String timeIn = null;
        String timeOut = null;

        ResultSet resultSet=SqlUtil.execute("SELECT time_in, time_out FROM attendance WHERE date = ? AND empId = ?;",date,empId);

        while (resultSet.next()) {
            timeIn = resultSet.getString("time_in");
            timeOut = resultSet.getString("time_out");

            System.out.println("Time In: " + timeIn + ", Time Out: " + timeOut);
        }
        calculate(timeIn, timeOut, empId, date);

    }

    @Override
    public void calculate(String timeIn, String timeOut, String empId, Date date) throws SQLException, ClassNotFoundException {
        String oldTimeText = timeIn;
        String newTimeText = timeOut;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Parse time strings to LocalTime
        LocalTime oldTime = LocalTime.parse(oldTimeText, formatter);
        LocalTime newTime = LocalTime.parse(newTimeText, formatter);

        // Calculate time difference
        Duration duration = Duration.between(oldTime, newTime);
        long hours = duration.toHours();

        double working = hours;

        SqlUtil.execute("UPDATE attendance set working_hours=? where empId=? && date=?;",working,empId,date);

    }

    @Override
    public int getTotalWorkingHours(String employeeId, LocalDate startDate, LocalDate endDate) throws SQLException, ClassNotFoundException {
        ResultSet resultSet= SqlUtil.execute("select sum(working_hours) from attendance where empId=? and date between ? and ?;",employeeId,startDate,endDate);

        int totalWorkingHours = 0;

        if (resultSet.next()) {
            totalWorkingHours = resultSet.getInt(1);

            System.out.println(totalWorkingHours);
        }

        return totalWorkingHours;

    }
}
