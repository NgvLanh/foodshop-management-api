package com.edu.java6asm.service;

import com.edu.java6asm.model.Dish;
import com.edu.java6asm.repository.DishRepository;
import com.edu.java6asm.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }
    public Dish getDishById(Long id) {
        return dishRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found"));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Dish createDish(Dish dish) {
        return dishRepository.save(dish);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public Dish updateDish(Long id, Dish dish) {
        return dishRepository.findById(id).map(existingDish -> {
            existingDish.setName(dish.getName());
            existingDish.setPrice(dish.getPrice());
            existingDish.setDescription(dish.getDescription());
            existingDish.setImage(dish.getImage());
            existingDish.setCategory(dish.getCategory());
            return dishRepository.save(existingDish);
        }).orElseThrow(() -> new RuntimeException("Dish not found"));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDish(Long id) {
        dishRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Dish updateDishStatus(Long id, Dish dish) {
        return dishRepository.findById(id).map(existingDish -> {
            existingDish.setStatus(!dish.getStatus());
            return dishRepository.save(existingDish);
        }).orElseThrow(() -> new RuntimeException("Dish not found"));
    }
    public List<Dish> getDishes() {
        return dishRepository.getDishByStatusTrue();
    }
    public String uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            return "Please select a file to upload.";
        }
        try {
            // Save the file using FileUploadUtil
            String fileName = file.getOriginalFilename();
            String filePath = FileUploadUtil.saveFile(fileName, file);

            // Return the file URL
            assert fileName != null;
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();

            return "File uploaded successfully: " + fileDownloadUri;
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to upload file.";
        }
    }
}
