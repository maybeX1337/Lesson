package com.example.demo.Services.Impl;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Goods;
import com.example.demo.Entity.Payloads.UpdateGoodsPayload;
import com.example.demo.Entity.Payloads.UpdateUserPayload;
import com.example.demo.Entity.User;
import com.example.demo.Repositories.GoodsRepository;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.GoodsService;
import com.example.demo.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository repository;

    @Override
    public Optional<Goods> findProduct(int id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<Goods> findGoods() {
        return repository.findAll();
    }

    @Override
    public Goods saveProduct(String name, String description, int price, List<Category> category) {
        return repository.save(new Goods(null, name, description, price, category));
    }

    @Override
    public void updateProduct(int id, UpdateGoodsPayload updateGoodsPayload) {
        repository.findById(id).ifPresent(good -> {

            good.setName(updateGoodsPayload.name());
            good.setDescription(updateGoodsPayload.description());
            good.setPrice(updateGoodsPayload.price());
            good.setCategory(updateGoodsPayload.category());

        });
    }

    @Override
    public void deleteProduct(int id) {
        repository.deleteById(id);
    }
}
