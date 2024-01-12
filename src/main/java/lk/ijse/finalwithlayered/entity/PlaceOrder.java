package lk.ijse.finalwithlayered.entity;

import lk.ijse.finalwithlayered.dto.tm.CartTm;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PlaceOrder {
    private String orderId;
    private LocalDate date;
    private String cusId;
    private String status;
    private List<CartTm> tmList = new ArrayList<>();


}
