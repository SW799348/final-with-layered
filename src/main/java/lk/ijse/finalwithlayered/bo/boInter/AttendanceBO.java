package lk.ijse.finalwithlayered.bo.boInter;

import lk.ijse.finalwithlayered.bo.SuperBO;
import lk.ijse.finalwithlayered.dto.AttendanceDto;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public interface AttendanceBO extends SuperBO {

    String generateNextAttendanceId() throws SQLException,ClassNotFoundException;

    boolean saveAttendance(AttendanceDto dto)throws SQLException,ClassNotFoundException;

    boolean updateAttendance(String empId, Date date, LocalTime time)throws SQLException,ClassNotFoundException;

    void calculateWorkingHours(String empId, Date date)throws SQLException,ClassNotFoundException;

    void calculate(String timeIn, String timeOut, String empId, Date date)throws SQLException,ClassNotFoundException;

    ArrayList<AttendanceDto> getAllAttendances()throws SQLException,ClassNotFoundException;

    boolean deleteAttendance(String id)throws SQLException,ClassNotFoundException;

    int getTotalWorkingHours(String employeeId, LocalDate startDate, LocalDate endDate)throws SQLException,ClassNotFoundException;




}
