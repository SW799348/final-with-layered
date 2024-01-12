package lk.ijse.finalwithlayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerDto {
    private String custId;
    private String name;
    private String address;
    private int tel;

}
