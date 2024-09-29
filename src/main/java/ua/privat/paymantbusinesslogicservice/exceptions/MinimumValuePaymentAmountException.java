package ua.privat.paymantbusinesslogicservice.exceptions;

public class MinimumValuePaymentAmountException extends RuntimeException {
    public MinimumValuePaymentAmountException(String message) {
        super(message);
    }
}
