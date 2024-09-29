package ua.privat.paymantbusinesslogicservice.exceptionHandlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.privat.paymantbusinesslogicservice.exceptions.*;


@ControllerAdvice
public class FieldValidate {

    @ExceptionHandler(FieldIsEmptyException.class)
    public ResponseEntity<String> fieldIsEmpty(FieldIsEmptyException fieldIsEmptyException) {
        return ResponseEntity.badRequest().body(fieldIsEmptyException.getMessage());
    }

    @ExceptionHandler(RecipientsSettlementAccountStructException.class)
    public ResponseEntity<String> recipientsSettlementAccountStruct(RecipientsSettlementAccountStructException recipientsSettlementAccountStructException) {
        return ResponseEntity.badRequest().body(recipientsSettlementAccountStructException.getMessage());
    }

    @ExceptionHandler(NotEnoughCharactersInTheFieldException.class)
    public ResponseEntity<String> notEnoughCharactersInTheField(NotEnoughCharactersInTheFieldException notEnoughCharactersInTheFieldException) {
        return ResponseEntity.badRequest().body(notEnoughCharactersInTheFieldException.getMessage());
    }

    @ExceptionHandler(MinimumValuePaymentAmountException.class)
    public ResponseEntity<String> minimumValuePaymentAmount(MinimumValuePaymentAmountException minimumValuePaymentAmountException) {
        return ResponseEntity.badRequest().body(minimumValuePaymentAmountException.getMessage());
    }

    @ExceptionHandler(OutOfBoundsAllowedCharsException.class)
    public ResponseEntity<String> outOfBoundsAllowedChars(OutOfBoundsAllowedCharsException outOfBoundsAllowedCharsException) {
        return ResponseEntity.badRequest().body(outOfBoundsAllowedCharsException.getMessage());
    }
}
