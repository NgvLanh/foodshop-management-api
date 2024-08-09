package com.edu.java6asm.repository;

import com.edu.java6asm.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {
    List<Dish> getDishByStatusTrue();
}
