package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "list", name = "t_goods")
public class Goods {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(name = "c_name", nullable = false)
    String name;
    @Column(name = "c_description")
    String description;
    @Column(name = "c_price")
    Integer price;
    @OneToMany
    @JoinColumn(name = "id")
    List<Category> category;
}
