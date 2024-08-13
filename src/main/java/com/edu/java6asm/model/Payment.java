package com.edu.java6asm.model;

import com.edu.java6asm.enums.PaymentMethods;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Double amount;
    LocalDate date = LocalDate.now();
    @Enumerated(EnumType.STRING)
    PaymentMethods paymentMethod;
    @OneToOne
    @JoinColumn(name = "order_id")
    Order order;
}
