package org.example.manager.Client;

import org.example.manager.Entity.Payloads.UpdateUserPayload;
import org.example.manager.Entity.User;

import java.util.List;
import java.util.Optional;

public interface UsersRestClient {
    List<User> findAllUsers();
    User createUser(String name, String email, int age);
    Optional<User> findUserById(int id);
    void updateUser(int id, UpdateUserPayload user);
    void deleteUser(int id);
}
