package ua.privat.paymantbusinesslogicservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ua.privat.paymantbusinesslogicservice.dto.WiringDTO;
import ua.privat.paymantbusinesslogicservice.exceptions.ServiceErrorException;


@Service
@RequiredArgsConstructor
public class WiringService {

    private final WebClient webClient;

    public ResponseEntity<WiringDTO> create(WiringDTO wiringDTO) {
        return webClient.post()
                .uri("/create-wiring")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(wiringDTO), WiringDTO.class)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceErrorException("The service returned an error with status code 5xx. More details: status code " + error.statusCode().value() + " error URL " + error.request().getURI())))
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new ServiceErrorException("The service returned an error with status code 4xx. More details: status code " + error.statusCode().value() + " error URL " + error.request().getURI())))
                .toEntity(WiringDTO.class)
                .block();
    }

    public ResponseEntity<WiringDTO> updateStatus(Long id, String status) {
        return webClient.patch()
                .uri(uriBuilder -> uriBuilder
                        .path("/update-wiring-status")
                        .queryParam("id", id)
                        .queryParam("status", status)
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new ServiceErrorException("The service returned an error with status code 4xx. More details: status code " + error.statusCode().value() + " error URL " + error.request().getURI())))
                .toEntity(WiringDTO.class)
                .block();
    }
}
