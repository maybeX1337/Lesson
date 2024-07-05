package com.example.demo.Services;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Goods;
import com.example.demo.Entity.Payloads.UpdateGoodsPayload;
import com.example.demo.Entity.Payloads.UpdateUserPayload;
import com.example.demo.Entity.User;

import java.util.List;
import java.util.Optional;

public interface GoodsService {
    Optional<Goods> findProduct(int id);

    Iterable<Goods> findGoods();

    Goods saveProduct(String name, String description, int price, List<Category> category);

    void deleteProduct(int id);

    void updateProduct(int id, UpdateGoodsPayload updateGoodsPayload);
}
