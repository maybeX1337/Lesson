package org.example.manager.repository;

import org.example.manager.Entity.User;
import org.example.manager.Entity.UserAuthority;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserAuthorityRepository extends CrudRepository<UserAuthority, Integer> {
    Optional<UserAuthority> findByUsername(String username);
}
