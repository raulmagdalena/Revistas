package com.revistas.exceptions;

public class MagazineNoIdException extends RuntimeException{
    public MagazineNoIdException(){
        super("No hi ha cap revista a la que afegir aquest número");
    }
}
