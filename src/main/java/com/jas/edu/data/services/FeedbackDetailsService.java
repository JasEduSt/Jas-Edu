package com.jas.edu.data.services;

import com.jas.edu.data.entity.Usersfeed;
import com.jas.edu.data.repository.FeedbackRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeedbackDetailsService {
    private final FeedbackRepository repository;

    public FeedbackDetailsService(FeedbackRepository reposoitory) {
        this.repository = reposoitory;
    }
    public Usersfeed update(Usersfeed entity){
        return repository.save(entity);
    }
    public int count() {
        return (int) repository.count();
    }
    public Page<Usersfeed> list(Pageable pageable) {
        return repository.findAll(pageable);
    }
    public Optional<Usersfeed> findUser(String  firstName , String email) {
        return repository.findUser(firstName,email);
    }
}
