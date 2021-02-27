package com.revistas.exceptions;

public class EditorNameAlreadyExists extends RuntimeException{

    public EditorNameAlreadyExists(String editorName){
        super("El nom d'editor " + editorName + " ja existeix.");
    }
}
