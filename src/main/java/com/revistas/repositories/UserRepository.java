package com.revistas.repositories;

import com.revistas.entities.User;
import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<User, Long> {

    public boolean findByEmail(String email);
}
