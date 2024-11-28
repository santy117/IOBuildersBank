package com.santiagosalvador.IOBuildersBank.exception;

public class WalletException {

    public static class WalletUpdateException extends RuntimeException {
        public WalletUpdateException(String message) {
            super(message);
        }

        public WalletUpdateException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class WalletNotFoundException extends RuntimeException {
        public WalletNotFoundException(String message) {
            super(message);
        }
    }

    public static class WalletAlreadyExistsException extends RuntimeException {
        public WalletAlreadyExistsException(String message) {
            super(message);
        }
    }
}
