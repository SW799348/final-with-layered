package lk.ijse.finalwithlayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SupplierDto {
    private String supplierId;

    private String name;

    private String contact_info;

    private String address;
}
