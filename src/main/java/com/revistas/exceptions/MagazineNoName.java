package com.revistas.exceptions;

public class MagazineNoName extends RuntimeException{

    public MagazineNoName() {
        super("Aquest nom de revista no existeix");
    }
}
