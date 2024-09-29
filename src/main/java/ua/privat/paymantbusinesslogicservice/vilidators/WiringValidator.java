package ua.privat.paymantbusinesslogicservice.vilidators;

import org.springframework.stereotype.Component;
import ua.privat.paymantbusinesslogicservice.exceptions.FieldIsEmptyException;
import ua.privat.paymantbusinesslogicservice.exceptions.MinimumValuePaymentAmountException;
import ua.privat.paymantbusinesslogicservice.exceptions.OutOfBoundsAllowedCharsException;
import ua.privat.paymantbusinesslogicservice.model.Wiring;

@Component
public class WiringValidator implements Valid<Wiring> {

    @Override
    public Wiring validate(Wiring wiring) {

        if (wiring.getWiringTime().isBlank()) throw new FieldIsEmptyException("Wiring time is empty!");
        if (wiring.getPaymentInstructionsId().toString().isBlank()) throw new FieldIsEmptyException("Payment instructions id is empty!");
        if (wiring.getPaymentAmount().toString().isBlank()) throw new FieldIsEmptyException("Payment amount is empty!");
        if (wiring.getStatus().isBlank()) throw new FieldIsEmptyException("Status is empty!");

        if (wiring.getPaymentAmount() < 0 || wiring.getPaymentAmount() == 0) throw new MinimumValuePaymentAmountException("The payment amount cannot be negative or equal to zero!");

        if (!wiring.getStatus().equals("A") && !wiring.getStatus().equals("S")) throw new OutOfBoundsAllowedCharsException("Valid characters for status \"A\" or \"S\"");

        return wiring;
    }
}
