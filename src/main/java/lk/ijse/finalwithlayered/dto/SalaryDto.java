package lk.ijse.finalwithlayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SalaryDto {
    private String salaryId;

    private String empId;

    private  String month;

    private int ot_hours;

    private Double total_salary;

    private int Amount_of_work_done_per_month;



}
