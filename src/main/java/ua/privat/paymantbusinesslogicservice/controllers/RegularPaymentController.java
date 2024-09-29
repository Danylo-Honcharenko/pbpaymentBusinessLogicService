package ua.privat.paymantbusinesslogicservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.privat.paymantbusinesslogicservice.dto.RegularPaymentDTO;
import ua.privat.paymantbusinesslogicservice.dto.WiringDTO;
import ua.privat.paymantbusinesslogicservice.dto.convertor.RegularPaymentConvertor;
import ua.privat.paymantbusinesslogicservice.exceptions.RegularPaymentsNotFoundException;
import ua.privat.paymantbusinesslogicservice.model.RegularPayment;
import ua.privat.paymantbusinesslogicservice.services.RegularPaymentService;
import ua.privat.paymantbusinesslogicservice.vilidators.Valid;

import java.util.List;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RegularPaymentController {

    private final Valid<RegularPayment> validator;
    private final RegularPaymentConvertor regularPaymentConvertor;
    private final RegularPaymentService regularPaymentService;

    @PostMapping("/create-regular-payment")
    public ResponseEntity<RegularPaymentDTO> createPayment(@RequestBody RegularPaymentDTO regularPaymentDTO) {
        RegularPayment regularPaymentValid = validator.validate(regularPaymentConvertor.convertToModel(regularPaymentDTO));
        return regularPaymentService.createRegularPayment(regularPaymentConvertor.convertToDTO(regularPaymentValid));
    }

    @GetMapping("/write-off-payment")
    public ResponseEntity<List<RegularPaymentDTO>> checkingTheNeedForWriteOff() {
        return ResponseEntity.ok().body(regularPaymentService.getRegularPaymentsNeedsWrittenOff());
    }

    @GetMapping("/regular-payment/payer")
    public ResponseEntity<List<RegularPaymentDTO>> getAllPaymentsByPayerFullName(@RequestParam String payerFullName) {
        List<RegularPaymentDTO> regularPaymentsList = regularPaymentService.getRegularPaymentsByPayerFullName(payerFullName);
        if (regularPaymentsList.isEmpty()) throw new RegularPaymentsNotFoundException("With such a full name there are no regular payments!");
        return ResponseEntity.ok().body(regularPaymentsList);
    }

    @GetMapping("/regular-payment/recipient")
    public ResponseEntity<List<RegularPaymentDTO>> getAllPaymentsByRecipientFullName(@RequestParam String recipientFullName) {
        List<RegularPaymentDTO> regularPaymentsList = regularPaymentService.getRegularPaymentsByRecipientFullName(recipientFullName);
        if (regularPaymentsList.isEmpty()) throw new RegularPaymentsNotFoundException("With such a recipient full name there are no regular payments!");
        return ResponseEntity.ok().body(regularPaymentsList);
    }

    @GetMapping("/wiring-write-off-history/{id}")
    public ResponseEntity<List<WiringDTO>> paymentWriteOffHistory(@PathVariable Long id) {
        List<WiringDTO> paymentWriteOffHistory = regularPaymentService.getPaymentWriteOffHistory(id);
        if (paymentWriteOffHistory.isEmpty()) throw new RegularPaymentsNotFoundException("With such id there are no wiring!");
        return ResponseEntity.ok().body(paymentWriteOffHistory);
    }
}
