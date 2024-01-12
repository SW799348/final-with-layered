package lk.ijse.finalwithlayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Material {
    private String materialId;
    private String description;
    private String qtyOnHand;
    private double unitPrice;
}
