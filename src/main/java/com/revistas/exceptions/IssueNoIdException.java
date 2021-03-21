package com.revistas.exceptions;

public class IssueNoIdException extends RuntimeException{
    public IssueNoIdException(){
        super("No hi ha cap número a la que afegir aquest article");
    }
}
