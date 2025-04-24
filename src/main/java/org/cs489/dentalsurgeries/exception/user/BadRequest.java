package org.cs489.dentalsurgeries.exception.user;

public class BadRequest extends RuntimeException{

    public BadRequest(String message) {
        super(message);
    }
}
