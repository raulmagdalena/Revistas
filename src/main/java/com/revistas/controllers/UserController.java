package com.revistas.controllers;

import com.revistas.entities.User;
import com.revistas.exceptions.UserAlreadyExistsException;
import com.revistas.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    private UserRepository repository;

    public User registerNewUserAccount(User user) throws UserAlreadyExistsException{
        if (emailExists(user.getEmail())) {
           throw new UserAlreadyExistsException("There is an account with that email address: " + user.getEmail());
        }
    return user;
    }
    private boolean emailExists(String email){
        return repository.findByEmail(email);
    }
}
