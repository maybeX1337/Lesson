package com.example.demo.Services;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Payloads.UpdateCategoryPayload;

import java.util.Optional;

public interface CategoryService {
    Optional<Category> findCategory(int id);

    Iterable<Category> findCategories();

    Category saveCategory(String name);

    void deleteCategory(int id);

    void updateCategory(int id, UpdateCategoryPayload updateCategoryPayload);
}
