package com.revistas.repositories;

import com.revistas.entities.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    public User save(User user);
    public User findByEmail(String email);
    public Long count();
}
