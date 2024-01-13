package lk.ijse.finalwithlayered.bo.boImpl;

import lk.ijse.finalwithlayered.bo.boInter.AttendanceBO;
import lk.ijse.finalwithlayered.dao.DAOFactory;
import lk.ijse.finalwithlayered.dao.interfaces.AttendanceDAO;
import lk.ijse.finalwithlayered.dto.AttendanceDto;
import lk.ijse.finalwithlayered.dto.CustomerDto;
import lk.ijse.finalwithlayered.entity.Attendance;
import lk.ijse.finalwithlayered.entity.Customer;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class AttendanceBOImpl implements AttendanceBO {

    AttendanceDAO attendanceDAO= (AttendanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ATTENDANCE);
    @Override
    public String generateNextAttendanceId() throws SQLException, ClassNotFoundException {
        return attendanceDAO.generateNewId();
    }

    @Override
    public boolean saveAttendance(AttendanceDto dto) throws SQLException, ClassNotFoundException {
        return attendanceDAO.save(new Attendance(dto.getAttendanceId(),dto.getEmpId(),dto.getDate(),dto.getTime_in(),dto.getTime_out(),dto.getWorking_hours()));
    }

    @Override
    public boolean updateAttendance(String empId, Date date, LocalTime time) throws SQLException, ClassNotFoundException {
        return attendanceDAO.updateAttendance(empId,date,time);
    }

    @Override
    public void calculateWorkingHours(String empId, Date date) throws SQLException, ClassNotFoundException {
        attendanceDAO.calculateWorkingHours(empId,date);
    }

    @Override
    public void calculate(String timeIn, String timeOut, String empId, Date date) throws SQLException, ClassNotFoundException {
        attendanceDAO.calculate(timeIn,timeOut,empId,date);
    }

    @Override
    public ArrayList<AttendanceDto> getAllAttendances() throws SQLException, ClassNotFoundException {
        ArrayList<Attendance> attendances = attendanceDAO.getAll();
        ArrayList<AttendanceDto> attendanceDtos = new ArrayList<>();

        for (Attendance attendance : attendances){
            attendanceDtos.add(new AttendanceDto(attendance.getAttendanceId(),attendance.getEmpId(),attendance.getDate(),attendance.getTime_in(),attendance.getTime_out(),attendance.getWorking_hours()));
        }
        return attendanceDtos;
    }

    @Override
    public boolean deleteAttendance(String id) throws SQLException, ClassNotFoundException {
        return attendanceDAO.delete(id);
    }

    @Override
    public int getTotalWorkingHours(String employeeId, LocalDate startDate, LocalDate endDate) throws SQLException, ClassNotFoundException {
        return attendanceDAO.getTotalWorkingHours(employeeId,startDate,endDate);
    }
}
