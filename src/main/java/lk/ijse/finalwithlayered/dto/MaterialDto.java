package lk.ijse.finalwithlayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class MaterialDto {
    private String materialId;
    private String description;
    private String qtyOnHand;
    private double unitPrice;
}
