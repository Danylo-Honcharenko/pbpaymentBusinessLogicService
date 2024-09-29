package ua.privat.paymantbusinesslogicservice.exceptions;

public class RegularPaymentsNotFoundException extends RuntimeException {
  public RegularPaymentsNotFoundException(String message) {
    super(message);
  }
}
