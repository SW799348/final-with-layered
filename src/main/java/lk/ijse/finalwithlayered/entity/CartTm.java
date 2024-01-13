package lk.ijse.finalwithlayered.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode

public class CartTm {
    private String materialId;
    private String description;
    private String size;
    private double unitPrice;
    private int requiredMaterialQty;
    private int qtyOfShirts;
    private double total;
}


