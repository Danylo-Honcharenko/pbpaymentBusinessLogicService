package ua.privat.paymantbusinesslogicservice.exceptions;

public class NotEnoughCharactersInTheFieldException extends RuntimeException {
    public NotEnoughCharactersInTheFieldException(String message) {
        super(message);
    }
}
