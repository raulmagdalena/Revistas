package com.revistas.exceptions;

public class IssueNotFoundException  extends RuntimeException{
    public IssueNotFoundException(Long issueId){
        super("No s'ha trovat aquesta edició");
    }
}
