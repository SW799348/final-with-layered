package lk.ijse.finalwithlayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AttendanceDto {
    private String attendanceId;
    private String empId;
    private Date date;
    private LocalTime time_in;
    private LocalTime time_out;
    private double working_hours;


}

