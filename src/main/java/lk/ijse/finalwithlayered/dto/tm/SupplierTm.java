package lk.ijse.finalwithlayered.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SupplierTm {
    private String supplierId;

    private String name;

    private String contact_info;

    private String address;
}
