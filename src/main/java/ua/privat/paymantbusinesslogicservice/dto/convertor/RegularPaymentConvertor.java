package ua.privat.paymantbusinesslogicservice.dto.convertor;

import org.springframework.stereotype.Component;
import ua.privat.paymantbusinesslogicservice.model.RegularPayment;

import java.sql.Timestamp;

@Component
public class RegularPaymentConvertor implements Converter<RegularPayment, ua.privat.paymantbusinesslogicservice.dto.RegularPaymentDTO> {

    @Override
    public RegularPayment convertToModel(ua.privat.paymantbusinesslogicservice.dto.RegularPaymentDTO regularPaymentDTO) {
        return RegularPayment.builder()
                .id(regularPaymentDTO.getId())
                .payerFullName(regularPaymentDTO.getPayerFullName())
                .iin(regularPaymentDTO.getIin())
                .cardNumber(regularPaymentDTO.getCardNumber())
                .recipientsSettlementAccount(regularPaymentDTO.getRecipientsSettlementAccount())
                .mfoRecipient(regularPaymentDTO.getMfoRecipient())
                .okpoRecipient(regularPaymentDTO.getOkpoRecipient())
                .recipientName(regularPaymentDTO.getRecipientName())
                .writeOffPeriod(regularPaymentDTO.getWriteOffPeriod())
                .paymentAmount(regularPaymentDTO.getPaymentAmount())
                .build();
    }

    @Override
    public ua.privat.paymantbusinesslogicservice.dto.RegularPaymentDTO convertToDTO(RegularPayment regularPayment) {
        return ua.privat.paymantbusinesslogicservice.dto.RegularPaymentDTO.builder()
                .id(regularPayment.getId())
                .payerFullName(regularPayment.getPayerFullName())
                .iin(regularPayment.getIin())
                .cardNumber(regularPayment.getCardNumber())
                .recipientsSettlementAccount(regularPayment.getRecipientsSettlementAccount())
                .mfoRecipient(regularPayment.getMfoRecipient())
                .okpoRecipient(regularPayment.getOkpoRecipient())
                .recipientName(regularPayment.getRecipientName())
                .writeOffPeriod(regularPayment.getWriteOffPeriod())
                .paymentAmount(regularPayment.getPaymentAmount())
                .build();
    }
}
