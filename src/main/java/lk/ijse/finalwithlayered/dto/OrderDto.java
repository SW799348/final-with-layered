package lk.ijse.finalwithlayered.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class OrderDto {
    private String orderId;
    private LocalDate date;
    private String custId;
    private String paymentStatus;

}
