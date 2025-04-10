package ua.privat.paymantbusinesslogicservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.privat.clientlib.utils.PaymentApiUtilI;
import ua.privat.paymantbusinesslogicservice.services.RegularPaymentServiceI;
import ua.privat.utils.dto.RegularPaymentDTO;
import ua.privat.utils.dto.WiringDTO;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegularPaymentService implements RegularPaymentServiceI {

    private final PaymentApiUtilI apiUtil;

    @Override
    public ResponseEntity<RegularPaymentDTO> createRegularPayment(RegularPaymentDTO regularPaymentDTO) {
        return apiUtil.doPost("/create-regular-payment", RegularPaymentDTO.class, regularPaymentDTO,
                (mono) -> mono);
    }

    @Override
    public List<RegularPaymentDTO> getRegularPaymentsNeedsWrittenOff() {
        return apiUtil.doGetList("/regular-payments", RegularPaymentDTO.class,
                (flux) ->
                        flux.filter(regularPaymentDTO -> new Timestamp(System.currentTimeMillis()).compareTo(Timestamp.valueOf(regularPaymentDTO.getWriteOffDate())) == 1));
    }

    @Override
    public List<RegularPaymentDTO> getRegularPaymentsByPayerFullName(String payerFullName) {
        return apiUtil.doGetList("/regular-payments", RegularPaymentDTO.class,
                (flux) ->
                        flux.filter(regularPaymentDTO -> regularPaymentDTO.getPayerFullName().equals(payerFullName)));
    }

    @Override
    public List<RegularPaymentDTO> getRegularPaymentsByRecipientFullName(String recipientFullName) {
        return apiUtil.doGetList("/regular-payments", RegularPaymentDTO.class,
                (flux) ->
                        flux.filter(regularPaymentDTO -> regularPaymentDTO.getRecipientName().equals(recipientFullName)));
    }

    @Override
    public List<WiringDTO> getPaymentWriteOffHistory(Long regularPaymentId) {
        return apiUtil.doGetList("/wiring", WiringDTO.class,
                (flux) ->
                        flux.filter(wiringDTO -> wiringDTO.getPaymentInstructionsId().equals(regularPaymentId)
                                && new Timestamp(System.currentTimeMillis()).compareTo(Timestamp.valueOf(wiringDTO.getWiringTime())) == 1
                                && wiringDTO.getStatus().equals("A")));
    }
}