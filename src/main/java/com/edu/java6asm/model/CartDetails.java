package com.edu.java6asm.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cart_details")
public class CartDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Integer quantity;
    Boolean isSelected = true;
    @ManyToOne
    @JoinColumn(name = "cart_id")
    Cart cart;

    @OneToOne
    @JoinColumn(name = "dish_id")
    Dish dish;
}
