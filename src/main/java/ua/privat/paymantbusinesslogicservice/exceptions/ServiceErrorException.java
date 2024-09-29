package ua.privat.paymantbusinesslogicservice.exceptions;

public class ServiceErrorException extends RuntimeException {
    public ServiceErrorException(String message) {
        super(message);
    }
}
