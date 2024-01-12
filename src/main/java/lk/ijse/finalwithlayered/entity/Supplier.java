package lk.ijse.finalwithlayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Supplier {
    private String supplierId;

    private String name;

    private String contact_info;

    private String address;
}
