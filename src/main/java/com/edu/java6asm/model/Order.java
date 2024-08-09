package com.edu.java6asm.model;

import com.edu.java6asm.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    // Trạng thái đơn hàng
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // Tổng giá trị của các mặt hàng trước khi áp dụng giảm giá
    private Double subtotal;

    // Số tiền giảm giá
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    // Số tiền phải thanh toán sau khi áp dụng giảm giá
    private Double total;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
}
