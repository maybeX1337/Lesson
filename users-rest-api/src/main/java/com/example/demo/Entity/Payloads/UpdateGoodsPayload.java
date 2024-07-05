package com.example.demo.Entity.Payloads;

import com.example.demo.Entity.Category;

import java.util.List;

public record UpdateGoodsPayload(
        String name,

        String description,

        Integer price,
        List<Category> category){

}
