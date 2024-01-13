package lk.ijse.finalwithlayered.dao.interfaces;

import lk.ijse.finalwithlayered.dao.CrudUtil;
import lk.ijse.finalwithlayered.entity.Attendance;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public interface AttendanceDAO extends CrudUtil<Attendance> {

    boolean updateAttendance(String empId, Date date, LocalTime time) throws SQLException, ClassNotFoundException;

    void calculateWorkingHours(String empId, Date date) throws SQLException, ClassNotFoundException;

    void calculate(String timeIn, String timeOut, String empId, Date date) throws SQLException, ClassNotFoundException;

    int getTotalWorkingHours(String employeeId, LocalDate startDate, LocalDate endDate) throws SQLException, ClassNotFoundException;

}
