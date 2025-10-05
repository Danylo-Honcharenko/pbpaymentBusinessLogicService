package ua.privat.paymantbusinesslogicservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.privat.clientlib.http.request.RegularPaymentInstructionsRequest;
import ua.privat.clientlib.http.response.RegularPaymentCreateResponse;
import ua.privat.clientlib.http.response.RegularPaymentListResponse;
import ua.privat.clientlib.http.response.RegularPaymentResponse;
import ua.privat.paymantbusinesslogicservice.services.RegularPaymentServiceI;

/**
 * Класс контроллер для работы с инструкциями регулярных платежей
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class RegularPaymentController {
    // Сервис для работы с инструкциями регулярных платежей
    private final RegularPaymentServiceI regularPaymentService;

    /**
     * Создать инструкцию регулярного платежа
     *
     * @param regularPaymentInstructionsRequest запрос
     * @return RegularPaymentCreateResponse ответ API
     */
    @PostMapping("/create-regular-payment")
    public RegularPaymentCreateResponse createPayment(@RequestBody @Valid RegularPaymentInstructionsRequest regularPaymentInstructionsRequest) {
        return new RegularPaymentCreateResponse(this.regularPaymentService.createRegularPayment(regularPaymentInstructionsRequest));
    }

    /**
     * Получить регулярные платежи по плательщику
     *
     * @param payerId ID плательщика
     * @return RegularPaymentListResponse ответ API
     */
    @GetMapping("/regular-payment/payer")
    public RegularPaymentListResponse getAllPaymentsByPayerId(@RequestParam(name = "id") Long payerId) {
        return new RegularPaymentListResponse(this.regularPaymentService.getRegularPaymentsByPayerId(payerId));
    }

    /**
     * Получить регулярные платежи по получателю
     *
     * @param recipientId ID получателя
     * @return RegularPaymentListResponse ответ API
     */
    @GetMapping("/regular-payment/recipient")
    public RegularPaymentListResponse getAllPaymentsByRecipientId(@RequestParam(name = "id") Long recipientId) {
        return new RegularPaymentListResponse(this.regularPaymentService.getRegularPaymentsByRecipientId(recipientId));
    }

    /**
     * Получить список регулярных платежей, которым требуется проводка
     *
     * @return RegularPaymentListResponse ответ API
     */
    @GetMapping("/regular-payment/need-to-write-off")
    public RegularPaymentListResponse getPaymentNeedToWriteOff() {
        return new RegularPaymentListResponse(this.regularPaymentService.getPaymentNeedToWriteOff());
    }

    /**
     * Обновить дату списания
     *
     * @param paymentId ID платежа
     * @return RegularPaymentResponse ответ API
     */
    @PatchMapping("/regular-payment/update-write-off-date")
    public RegularPaymentResponse updateWriteOffDate(@RequestParam(name = "id") Long paymentId) {
        return new RegularPaymentResponse(this.regularPaymentService.updateWriteOffDate(paymentId));
    }
}
