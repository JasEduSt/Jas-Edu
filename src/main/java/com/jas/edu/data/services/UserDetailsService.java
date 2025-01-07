package com.jas.edu.data.services;

import com.jas.edu.data.entity.UserDetails;
import com.jas.edu.data.repository.UserDetaillsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsService {
    private final UserDetaillsRepository repository;

    public UserDetailsService(UserDetaillsRepository reposoitory) {
        this.repository = reposoitory;
    }
    public UserDetails update(UserDetails entity){
        return repository.save(entity);
    }
    public int count() {
        return (int) repository.count();
    }
    public Page<UserDetails> list(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public Optional<UserDetails> findUser(String  firstName , String email) {
        return repository.findUser(firstName,email);
    }
    public UserDetails findByUsernameAndPassword(String userName, String password) {
        return repository.findByUsernameAndPassword(userName, password);
    }

    public String findPassword(String username) {
        return repository.findPassword(username);
    }
}
