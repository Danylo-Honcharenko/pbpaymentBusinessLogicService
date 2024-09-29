package ua.privat.paymantbusinesslogicservice.exceptions;

public class FieldIsEmptyException extends RuntimeException {
    public FieldIsEmptyException(String message) {
        super(message);
    }
}
