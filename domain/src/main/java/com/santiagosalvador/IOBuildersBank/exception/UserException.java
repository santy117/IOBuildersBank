package com.santiagosalvador.IOBuildersBank.exception;

public class UserException{
    public static class UserAlreadyExistsException extends RuntimeException {
        public UserAlreadyExistsException(String message) {
            super(message);
        }
    }
}
