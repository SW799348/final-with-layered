package lk.ijse.finalwithlayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeDto {

    private String empId;
    private String name;
    private String address;
    private int tel;
    private String userId ;


}
