package ua.privat.paymantbusinesslogicservice.services;

import org.springframework.http.ResponseEntity;
import ua.privat.utils.dto.RegularPaymentDTO;
import ua.privat.utils.dto.WiringDTO;

import java.util.List;

public interface RegularPaymentServiceI {
    ResponseEntity<RegularPaymentDTO> createRegularPayment(RegularPaymentDTO regularPaymentDTO);
    List<RegularPaymentDTO> getRegularPaymentsNeedsWrittenOff();
    List<RegularPaymentDTO> getRegularPaymentsByPayerFullName(String payerFullName);
    List<RegularPaymentDTO> getRegularPaymentsByRecipientFullName(String recipientFullName);
    List<WiringDTO> getPaymentWriteOffHistory(Long regularPaymentId);
}
