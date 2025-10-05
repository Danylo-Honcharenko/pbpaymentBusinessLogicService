package ua.privat.paymantbusinesslogicservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.privat.clientlib.http.WiringDataClient;
import ua.privat.clientlib.http.request.WiringRequest;
import ua.privat.clientlib.http.response.data.WiringData;
import ua.privat.paymantbusinesslogicservice.consts.WiringStatus;
import ua.privat.paymantbusinesslogicservice.services.WiringServiceI;

/**
 * Сервис для работы с проводками
 */
@Service
@RequiredArgsConstructor
public class WiringService implements WiringServiceI {
    // Класс для взаимодействия с сервисом хранения данных о проводках
    private final WiringDataClient wiringClient;

    /**
     * {@inheritDoc}
     */
    @Override
    public WiringData create(WiringRequest wiringRequest) {
        wiringRequest.setStatus(WiringStatus.ACTIVE.getStatus());
        return new WiringData(this.wiringClient.createWiring(wiringRequest));
    }
}