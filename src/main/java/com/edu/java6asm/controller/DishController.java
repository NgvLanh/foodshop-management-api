package com.edu.java6asm.controller;

import com.edu.java6asm.model.Dish;
import com.edu.java6asm.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping
    public ResponseEntity<?> getAllDishes() {
        var dishes = dishService.getAllDishes();
        return ResponseEntity.ok(dishes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDishById(@PathVariable Long id) {
        var dish = dishService.getDishById(id);
        return ResponseEntity.ok(dish);
    }

    @PostMapping
    public ResponseEntity<?> createDish(@RequestBody Dish dish) {
        var createdDish = dishService.createDish(dish);
        return ResponseEntity.ok(createdDish);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDish(@PathVariable Long id, @RequestBody Dish dish) {
        var updatedDish = dishService.updateDish(id, dish);
        return ResponseEntity.ok(updatedDish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/status/{id}")
    public ResponseEntity<?> updateDishStatus(@PathVariable Long id, @RequestBody Dish dish) {
        var updatedDish = dishService.updateDishStatus(id, dish);
        return ResponseEntity.ok(updatedDish);
    }

    @GetMapping("/status-true")
    public ResponseEntity<?> getDishes() {
        var dishes = dishService.getDishes();
        return ResponseEntity.ok(dishes);
    }

    @PostMapping("/uploads")
    public ResponseEntity<?> uploadFile(@RequestParam(value = "image", required = false) MultipartFile image) {
        String response = dishService.uploadFile(image);
        return ResponseEntity.ok(response);
    }
}
