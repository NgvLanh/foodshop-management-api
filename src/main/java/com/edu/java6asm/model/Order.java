package com.edu.java6asm.model;

import com.edu.java6asm.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    LocalDate date = LocalDate.now();
    // Trạng thái đơn hàng
    @Enumerated(EnumType.STRING)
    OrderStatus status;
    // Tổng giá trị của các mặt hàng trước khi áp dụng giảm giá
    Double subtotal;
    // Số tiền giảm giá
    @ManyToOne
    @JoinColumn(name = "discount_id")
    Discount discount;
    // Số tiền phải thanh toán sau khi áp dụng giảm giá
    Double total;
    @ManyToOne
    @JoinColumn(name = "address_id")
    Address address;
}
