package com.revistas.exceptions;

public class EditorNoName extends RuntimeException{

    public EditorNoName(){
        super("No hi ha cap nom d'editor");
    }
}
