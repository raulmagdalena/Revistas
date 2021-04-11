package com.revistas.controllers;

import com.revistas.entities.User;
import com.revistas.exceptions.UserAlreadyExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class RegistrationController {

    UserController userController;

    public RegistrationController(UserController userController){
        this.userController = userController;
    }

    @GetMapping("/users/registration")
    public String showRegistrationForm(WebRequest request, Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "/users/registration";
    }

    @PostMapping("/users/registration")
    public ModelAndView registerUserAccount(@ModelAttribute("user") User user, HttpServletRequest request, Errors errors){
        try {
            User registered = userController.registerNewUserAccount(user);
        } catch (UserAlreadyExistsException uaeEx){
            return new ModelAndView("userExists", "user", user);
        }
        return new ModelAndView("successRegister", "user", user);
    }
}
