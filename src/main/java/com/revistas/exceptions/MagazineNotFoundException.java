package com.revistas.exceptions;

public class MagazineNotFoundException extends RuntimeException {
    public MagazineNotFoundException(Long idMagazine){
        super("No S'ha trobat la revista amb el id:" + idMagazine);
    }
}
