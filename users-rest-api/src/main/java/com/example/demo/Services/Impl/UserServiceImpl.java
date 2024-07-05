package com.example.demo.Services.Impl;

import com.example.demo.Entity.Payloads.UpdateUserPayload;
import com.example.demo.Entity.User;
import com.example.demo.Repositories.UserRepository;
import com.example.demo.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public Optional<User> findUser(int id) {
        return repository.findById(id);
    }

    @Override
    public Iterable<User> findUsers() {
        return repository.findAll();
    }

    @Override
    public User saveUser(String name, String email, int age) {
        return repository.save(new User(null, name, email, age));
    }

    @Override
    public void updateUser(int id, UpdateUserPayload updateUserPayload) {
        repository.findById(id).ifPresent(user -> {
            user.setEmail(updateUserPayload.email());
            user.setName(updateUserPayload.name());
            user.setAge(updateUserPayload.age());
        });
    }

    @Override
    public void deleteUser(int id) {
        repository.deleteById(id);
    }
}
