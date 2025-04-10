package ua.privat.paymantbusinesslogicservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.privat.paymantbusinesslogicservice.vilidators.Valid;
import ua.privat.paymantbusinesslogicservice.services.impl.WiringService;
import ua.privat.utils.dto.WiringDTO;
import ua.privat.utils.dto.convertor.WiringConverter;
import ua.privat.utils.models.Wiring;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class WiringController {

    private final Valid<Wiring> validator;
    private final WiringConverter wiringConverter;
    private final WiringService wiringService;

    @PostMapping("/create-wiring")
    public ResponseEntity<WiringDTO> createWiring(@RequestBody WiringDTO wiringDTO) {
        Wiring wiring = validator.validate(wiringConverter.convertToModel(wiringDTO));
        return wiringService.create(wiringConverter.convertToDTO(wiring));
    }

    @GetMapping("/reversal-wiring/{id}")
    public ResponseEntity<WiringDTO> reversalWiring(@PathVariable Long id) {
        return wiringService.updateStatus(id, "S");
    }
}
