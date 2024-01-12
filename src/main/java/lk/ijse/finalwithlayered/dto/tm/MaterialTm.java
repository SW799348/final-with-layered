package lk.ijse.finalwithlayered.dto.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class MaterialTm {
    private String materialId;
    private String description;
    private String qtyOnHand;
    private double unitPrice;
}
