package ua.privat.paymantbusinesslogicservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ua.privat.clientlib.http.request.WiringRequest;
import ua.privat.clientlib.http.response.WiringCreateResponse;
import ua.privat.paymantbusinesslogicservice.services.WiringServiceI;

/**
 * Класс контроллер для работы с проводками
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class WiringController {
    // Сервис для работы с проводками
    private final WiringServiceI wiringService;

    /**
     * Создать проводку
     *
     * @param wiringRequest запрос на создание проводки
     * @return WiringCreateResponse ответ API
     */
    @PostMapping("/create-wiring")
    public WiringCreateResponse createWiring(@RequestBody WiringRequest wiringRequest) {
        return new WiringCreateResponse(this.wiringService.create(wiringRequest));
    }
}
