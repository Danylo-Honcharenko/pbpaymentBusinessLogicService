package ua.privat.paymantbusinesslogicservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.privat.clientlib.http.RegularPaymentInstructionsDataClient;
import ua.privat.clientlib.http.request.RegularPaymentInstructionsRequest;
import ua.privat.clientlib.http.request.ext.RegularPaymentInstructionsExtRequest;
import ua.privat.clientlib.http.response.RegularPaymentInstructionsResponse;
import ua.privat.clientlib.http.response.data.RegularPaymentData;
import ua.privat.paymantbusinesslogicservice.consts.RegularPaymentStatus;
import ua.privat.paymantbusinesslogicservice.convertors.RegularPaymentInstructionsResponseToRegularPaymentData;
import ua.privat.paymantbusinesslogicservice.date.helper.DateHelper;
import ua.privat.paymantbusinesslogicservice.services.RegularPaymentServiceI;

import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * Сервис для работы с регулярными платежами
 */
@Service
@RequiredArgsConstructor
public class RegularPaymentService implements RegularPaymentServiceI {
    // Класс для взаимодействия с сервисом хранения данных о регулярных платежах
    private final RegularPaymentInstructionsDataClient regularPaymentInstructionsDataClient;
    // Конвертор
    private final RegularPaymentInstructionsResponseToRegularPaymentData regularPaymentInstructionsResponseToRegularPaymentData;


    /**
     * {@inheritDoc}
     */
    @Override
    public RegularPaymentData createRegularPayment(RegularPaymentInstructionsRequest regularPaymentInstructionsRequest) {
        RegularPaymentInstructionsExtRequest regularPaymentInstructionsExtRequest = RegularPaymentInstructionsExtRequest.of(regularPaymentInstructionsRequest);
        this.setSateAndWriteOffDate(regularPaymentInstructionsExtRequest);
        return new RegularPaymentData(this.regularPaymentInstructionsDataClient.create(regularPaymentInstructionsExtRequest));
    }

    /**
     * Устанавливает след. дату списания в формате yyyy.MM.dd hh:mm:ss.SS и статус
     *
     * @param regularPaymentInstructions инструкция регулярного платежа
     */
    private void setSateAndWriteOffDate(RegularPaymentInstructionsExtRequest regularPaymentInstructions) {
        String writeOffPeriod = regularPaymentInstructions.getWriteOffPeriod();
        if (Objects.nonNull(writeOffPeriod)) {
            regularPaymentInstructions.setState(RegularPaymentStatus.ACTIVE.getStatus());
            regularPaymentInstructions.setWriteoffdate(DateHelper.getDateByExpression(writeOffPeriod));
        } else {
            regularPaymentInstructions.setState(RegularPaymentStatus.OFF.getStatus());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPaymentData> getRegularPaymentsByPayerId(Long payerId) {
        return this.regularPaymentInstructionsDataClient.getAllRegularPayments().stream()
                .filter((regPayment) -> regPayment.getPayerid().equals(payerId))
                .map(this.regularPaymentInstructionsResponseToRegularPaymentData::convert)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPaymentData> getRegularPaymentsByRecipientId(Long recipientId) {
        return this.regularPaymentInstructionsDataClient.getAllRegularPayments().stream()
                .filter((regPayment) -> regPayment.getRecipientid().equals(recipientId))
                .map(this.regularPaymentInstructionsResponseToRegularPaymentData::convert)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RegularPaymentData> getPaymentNeedToWriteOff() {
        return this.regularPaymentInstructionsDataClient.getAllRegularPayments().stream()
                .filter(regularPaymentInst -> Objects.nonNull(regularPaymentInst.getWriteoffdate()))
                .filter(this::isNeedWriteOffPayment)
                .map(this.regularPaymentInstructionsResponseToRegularPaymentData::convert)
                .toList();
    }

    /**
     * Проверяем нужно ли делать списание по платежу
     *
     * @param regularPaymentInst платёж
     * @return boolean true - нужно делать списание / false - не нужно
     */
    private boolean isNeedWriteOffPayment(RegularPaymentInstructionsResponse regularPaymentInst) {
        Date date = new Date();
        return regularPaymentInst.getState().equals(RegularPaymentStatus.ACTIVE.getStatus()) &&
                (regularPaymentInst.getWriteoffdate().before(date) || regularPaymentInst.getWriteoffdate().equals(date));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegularPaymentData updateWriteOffDate(Long paymentId) {
        RegularPaymentInstructionsResponse regularPaymentInstructionsResponse = this.regularPaymentInstructionsDataClient.getRegularPaymentById(paymentId).stream()
                .findFirst()
                .orElse(null);

        if (Objects.isNull(regularPaymentInstructionsResponse)) return new RegularPaymentData();

        RegularPaymentInstructionsExtRequest regularPaymentInstructionsExtRequest = RegularPaymentInstructionsExtRequest.of(RegularPaymentInstructionsRequest.builder().build());
        regularPaymentInstructionsExtRequest.setId(regularPaymentInstructionsResponse.getId());
        regularPaymentInstructionsExtRequest.setWriteoffdate(DateHelper.getDateByExpression(regularPaymentInstructionsResponse.getWriteOffPeriod()));

        return new RegularPaymentData(this.regularPaymentInstructionsDataClient.update(regularPaymentInstructionsExtRequest));
    }
}