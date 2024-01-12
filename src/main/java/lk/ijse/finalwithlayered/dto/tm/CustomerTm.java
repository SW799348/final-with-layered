package lk.ijse.finalwithlayered.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class CustomerTm {
    private String custId;
    private  String name;
    private String address;
    private int tel;

}
