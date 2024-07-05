package org.example.manager.Client;

import lombok.RequiredArgsConstructor;
import org.example.manager.Entity.Payloads.UpdateUserPayload;
import org.example.manager.Entity.Payloads.UserPayload;
import org.example.manager.Entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UsersRestClientRestClient implements UsersRestClient {
    private final RestClient restClient;

    private final ParameterizedTypeReference<List<User>> userListType= new ParameterizedTypeReference<List<User>>() {};

    @Override
    public List<User> findAllUsers() {
        return restClient
                .get()
                .uri("/users-api/users")
                .retrieve()
                .body(userListType);
    }

    @Override
    public User createUser(String name, String email, int age) {
        return restClient
                .post()
                .uri("/users-api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .body(new UserPayload(name, email, age))
                .retrieve()
                .body(User.class);
    }

    @Override
    public Optional<User> findUserById(int id) {
        return Optional.ofNullable(
                restClient
                        .get()
                        .uri("/users-api/user/{userId}", id)
                        .retrieve()
                        .body(User.class)
        );
    }

    @Override
    public void updateUser(int id, UpdateUserPayload user) {
        restClient
                .patch()
                .uri("/users-api/user/{userId}", id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(user)
                .retrieve()
                .toBodilessEntity();
    }

    @Override
    public void deleteUser(int id) {
        restClient
                .delete()
                .uri("/users-api/user/{userId}", id)
                .retrieve()
                .toBodilessEntity();
    }
}
