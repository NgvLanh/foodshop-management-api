package com.edu.java6asm.service;

import com.edu.java6asm.model.Category;
import com.edu.java6asm.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }
    @PreAuthorize("hasRole('ADMIN')")

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }
    @PreAuthorize("hasRole('ADMIN')")

    public Category updateCategory(Long id, Category category) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        return categoryRepository.save(existingCategory);
    }
    @PreAuthorize("hasRole('ADMIN')")

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
