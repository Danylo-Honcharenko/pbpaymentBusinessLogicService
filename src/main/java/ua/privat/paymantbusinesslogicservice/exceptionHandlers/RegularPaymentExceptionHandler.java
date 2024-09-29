package ua.privat.paymantbusinesslogicservice.exceptionHandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.privat.paymantbusinesslogicservice.exceptions.RegularPaymentsNotFoundException;

@ControllerAdvice
public class RegularPaymentExceptionHandler {

    @ExceptionHandler(RegularPaymentsNotFoundException.class)
    public ResponseEntity<String> regularPaymentsNotFoundException(RegularPaymentsNotFoundException regularPaymentsNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(regularPaymentsNotFoundException.getMessage());
    }
}
