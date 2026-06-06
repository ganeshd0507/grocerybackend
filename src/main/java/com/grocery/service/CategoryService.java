package com.grocery.service;

import com.grocery.entity.Category;
import com.grocery.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category createCategory(Category category) {
        if (category.getId() == null || category.getId().isEmpty()) {
            category.setId(category.getName().toLowerCase().replaceAll("[^a-z0-9]+", "-"));
        }
        return categoryRepository.save(category);
    }
}
