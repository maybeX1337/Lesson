package com.example.demo.Repositories;

import com.example.demo.Entity.Category;
import com.example.demo.Entity.Goods;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Integer> {

}
