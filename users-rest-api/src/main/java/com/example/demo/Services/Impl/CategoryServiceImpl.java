package com.example.demo.Services.Impl;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Goods;
import com.example.demo.Entity.Payloads.UpdateCategoryPayload;
import com.example.demo.Entity.Payloads.UpdateGoodsPayload;
import com.example.demo.Entity.Payloads.UpdateUserPayload;
import com.example.demo.Entity.User;
import com.example.demo.Repositories.CategoryRepository;
import com.example.demo.Repositories.GoodsRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.CategoryService;
import com.example.demo.Services.GoodsService;
import com.example.demo.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;

    @Override
    public Optional<Category> findCategory(int id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Category> findCategories() {
        return repository.findAll();
    }

    @Override
    public Category saveCategory(String name) {
        return repository.save(new Category(null, name));
    }

    @Override
    public void updateCategory(int id, UpdateCategoryPayload updateCategoryPayload) {
        repository.findById(id).ifPresent(good -> {

            good.setName(updateCategoryPayload.name());


        });
    }

    @Override
    public void deleteCategory(int id) {
        repository.deleteById(id);
    }
}
