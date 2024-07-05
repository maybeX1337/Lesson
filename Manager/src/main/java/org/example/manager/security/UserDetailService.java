package org.example.manager.security;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.example.manager.Entity.Roles;
import org.example.manager.repository.UserAuthorityRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private  final UserAuthorityRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        return repository.findByUsername(username)
                .map(user -> User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .authorities(user.getAuthorities()
                                .stream()
                                .map(Roles::getRole)
                                .map(SimpleGrantedAuthority::new)
                                .toList()).build()).orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username)));
    }
}
