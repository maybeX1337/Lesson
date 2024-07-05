//package com.example.demo.Repositories;
//
//import com.example.demo.Entity.User;
//import com.example.demo.Repositories.Impl.InMemoryUserRepository;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.Comparator;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public class InMemoryUserRepositoryImpl implements InMemoryUserRepository {
//    final List<User> users = new ArrayList<>();
//
//    public InMemoryUserRepositoryImpl() {
//        for (int i = 0; i < 5; i++) {
//            users.add(User.builder().id(i).age(18 + i).email("User" + i + "@gmail.com").name("User" + i).build());
//        }
//    }
//
//    @Override
//    public Optional<User> findById(int id) {
//        return users.stream().filter(user -> user.getId() == id).findFirst();
//    }
//
//    @Override
//    public List<User> findAll() {
//        return users;
//    }
//
//    @Override
//    public User create(User user) {
//        user.setId(users.stream().max(Comparator.comparing(User::getId)).map(User::getId).orElse(0) + 1);
//        users.add(user);
//        return user;
//    }
//
//    @Override
//    public void delete(int id) {
//        users.stream().filter(user -> user.getId() == id).findFirst().ifPresent(users::remove);
//    }
//}
