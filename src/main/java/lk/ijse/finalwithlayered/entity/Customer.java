package lk.ijse.finalwithlayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Customer {
    private String custId;
    private String name;
    private String address;
    private int tel;

}
