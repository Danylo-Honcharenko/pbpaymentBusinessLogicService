package ua.privat.paymantbusinesslogicservice.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.privat.clientlib.utils.PaymentApiUtilI;
import ua.privat.paymantbusinesslogicservice.services.WiringServiceI;
import ua.privat.utils.dto.WiringDTO;

@Service
@RequiredArgsConstructor
public class WiringService implements WiringServiceI {

    private final PaymentApiUtilI apiUtil;

    @Override
    public ResponseEntity<WiringDTO> create(WiringDTO wiringDTO) {
        return apiUtil.doPost("/create-wiring", WiringDTO.class, wiringDTO, (mono) -> mono);
    }

    @Override
    public ResponseEntity<WiringDTO> updateStatus(Long id, String status) {
        return apiUtil.doPatch(uriBuilder -> uriBuilder
                .path("/update-wiring-status")
                .queryParam("id", id)
                .queryParam("status", status)
                .build(), WiringDTO.class, (mono) -> mono);
    }
}