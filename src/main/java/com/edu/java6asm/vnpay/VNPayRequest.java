package com.edu.java6asm.vnpay;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VNPayRequest {
    Integer total;
    String orderInfo;
    String returnUrl;
}
