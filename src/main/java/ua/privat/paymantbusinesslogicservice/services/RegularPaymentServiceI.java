package ua.privat.paymantbusinesslogicservice.services;

import ua.privat.clientlib.http.request.RegularPaymentInstructionsRequest;
import ua.privat.clientlib.http.response.data.RegularPaymentData;

import java.util.List;

/**
 * Интерфейс сервиса для работы с регулярными платежами
 */
public interface RegularPaymentServiceI {
    /**
     * Создать инструкцию регулярного платежа
     *
     * @param regularPaymentInstructionsRequest запрос на создание инструкции регулярного платежа
     * @return RegularPaymentData регулярный платёж
     */
    RegularPaymentData createRegularPayment(RegularPaymentInstructionsRequest regularPaymentInstructionsRequest);
    /**
     * Получить регулярные платежи по плательщику
     *
     * @param payerId ID плательщика
     * @return List<RegularPaymentData> список инструкций регулярных платежей
     */
    List<RegularPaymentData> getRegularPaymentsByPayerId(Long payerId);
    /**
     * Получить регулярные платежи по получателю
     *
     * @param recipientId ID получателя
     * @return List<RegularPaymentData> список инструкций регулярных платежей
     */
    List<RegularPaymentData> getRegularPaymentsByRecipientId(Long recipientId);
    /**
     * Получить платежи, которые нужно списать
     *
     * @return List<RegularPaymentData> платежи которые нужно списать
     */
    List<RegularPaymentData> getPaymentNeedToWriteOff();
    /**
     * Обновить дату списания
     *
     * @param paymentId ID инструкции платежа
     * @return RegularPaymentData обновлённая инструкция платежа
     */
    RegularPaymentData updateWriteOffDate(Long paymentId);
}
