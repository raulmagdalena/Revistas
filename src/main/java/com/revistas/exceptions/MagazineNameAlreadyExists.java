package com.revistas.exceptions;

public class MagazineNameAlreadyExists extends RuntimeException{

    public MagazineNameAlreadyExists(String name){
        super("El nóm de revista " + name + " ja existeix.");
    }
}
