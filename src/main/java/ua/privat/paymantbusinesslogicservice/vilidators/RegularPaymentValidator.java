package ua.privat.paymantbusinesslogicservice.vilidators;

import org.springframework.stereotype.Component;
import ua.privat.paymantbusinesslogicservice.exceptions.*;
import ua.privat.paymantbusinesslogicservice.models.RegularPayment;


@Component
public class RegularPaymentValidator implements Valid<RegularPayment> {

    @Override
    public RegularPayment validate(RegularPayment regularPayment) {

        if (regularPayment.getPayerFullName().isBlank()) throw new FieldIsEmptyException("Payer full name is empty!");
        if (regularPayment.getIin().toString().isBlank()) throw new FieldIsEmptyException("INN is empty!");
        if (regularPayment.getCardNumber().toString().isBlank()) throw new FieldIsEmptyException("Card number is empty!");
        if (regularPayment.getRecipientsSettlementAccount().isBlank()) throw  new FieldIsEmptyException("Recipients settlement account is empty!");
        if (regularPayment.getMfoRecipient().toString().isBlank()) throw new FieldIsEmptyException("MFO recipient is empty!");
        if (regularPayment.getOkpoRecipient().toString().isBlank()) throw new FieldIsEmptyException("Okpo recipient is empty!");
        if (regularPayment.getRecipientName().isBlank()) throw new FieldIsEmptyException("Recipient name is empty!");
        if (regularPayment.getWriteOffPeriod().isBlank()) throw new FieldIsEmptyException("Write off period is empty!");
        if (regularPayment.getPaymentAmount().toString().isBlank()) throw new FieldIsEmptyException("Payment amount is empty!");

        if (!regularPayment.getRecipientsSettlementAccount().matches("^UA\\d+")) throw new RecipientsSettlementAccountStructException("The recipient's bank account must contain \"UA\" at the beginning of the line.");

        if (regularPayment.getIin().toString().length() != 10) throw new NotEnoughCharactersInTheFieldException("The INN must contain 10 characters!");
        if (regularPayment.getCardNumber().toString().length() != 16) throw new NotEnoughCharactersInTheFieldException("The card number must contain 16 characters!");
        if (regularPayment.getRecipientsSettlementAccount().length() != 29) throw new NotEnoughCharactersInTheFieldException("The recipient's bank account must contain 29 characters!");
        if (regularPayment.getMfoRecipient().toString().length() != 6) throw new NotEnoughCharactersInTheFieldException("The MFO recipient must contain 6 characters!");
        if (regularPayment.getOkpoRecipient().toString().length() != 8) throw new NotEnoughCharactersInTheFieldException("The OKPO recipient must contain 8 characters!");

        if (regularPayment.getPaymentAmount() < 0 || regularPayment.getPaymentAmount() == 0) throw new MinimumValuePaymentAmountException("The payment amount cannot be negative or equal to zero!");

        if (!regularPayment.getWriteOffPeriod().matches("\\d/\\d/\\d+")) throw new ViolationOfStructureException("Violation of the structure of the field \"writeOffPeriod\"!");

        return regularPayment;
    }
}
