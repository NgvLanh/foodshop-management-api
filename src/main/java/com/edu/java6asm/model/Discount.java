package com.edu.java6asm.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity(name = "discounts")
public class Discount {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    Long id;
    @Column(name = "code", unique = true)
    String code;
    Date createAt;
    Date dateEnd;
    Integer quantity;
    Double quota;
    Double percentNumber;
}
