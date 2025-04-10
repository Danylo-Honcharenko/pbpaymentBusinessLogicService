package ua.privat.paymantbusinesslogicservice.services;

import org.springframework.http.ResponseEntity;
import ua.privat.utils.dto.WiringDTO;

public interface WiringServiceI {
    ResponseEntity<WiringDTO> create(WiringDTO wiringDTO);
    ResponseEntity<WiringDTO> updateStatus(Long id, String status);
}
