package com.revistas.exceptions;

public class EditorNotFoundException  extends RuntimeException{
    public EditorNotFoundException(long editorId){
        //TODO internationalize message
        super("No s'ha trobat l'editor amb el id:" + editorId);
    }
}
