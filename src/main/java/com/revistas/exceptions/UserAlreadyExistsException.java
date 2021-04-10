package com.revistas.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String userName){
        super("L'usuari ja existeix.");
    }
}
