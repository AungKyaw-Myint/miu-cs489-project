package org.cs489.dentalsurgeries.exception.user;

public class DuplicateDataException extends RuntimeException{
    public DuplicateDataException(String message){
        super(message);
    }
}
