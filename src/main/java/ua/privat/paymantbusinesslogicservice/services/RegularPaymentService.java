package ua.privat.paymantbusinesslogicservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ua.privat.paymantbusinesslogicservice.dto.RegularPaymentDTO;
import ua.privat.paymantbusinesslogicservice.dto.WiringDTO;
import ua.privat.paymantbusinesslogicservice.exceptions.ServiceErrorException;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class RegularPaymentService {

    private final WebClient webClient;

    public ResponseEntity<RegularPaymentDTO> createRegularPayment(RegularPaymentDTO regularPaymentDTO) {
        return webClient.post()
                .uri("/create-regular-payment")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(regularPaymentDTO), RegularPaymentDTO.class)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new ServiceErrorException("The service returned an error with status code 5xx. More details: " + error)))
                .toEntity(RegularPaymentDTO.class)
                .block();
    }

    public List<RegularPaymentDTO> getRegularPaymentsNeedsWrittenOff() {
        Iterable<RegularPaymentDTO> regularPaymentDtoS = webClient.get()
                .uri("/regular-payments")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new ServiceErrorException("The service returned an error with status code 4xx. More details: status code " + error.statusCode().value() + " error URL " + error.request().getURI())))
                .bodyToFlux(RegularPaymentDTO.class)
                .filter(regularPaymentDTO -> new Timestamp(System.currentTimeMillis())
                                .compareTo(Timestamp.valueOf(regularPaymentDTO.getWriteOffDate())) == 1)
                .toIterable();

        return StreamSupport.stream(regularPaymentDtoS.spliterator(), false)
                .toList();
    }

    public List<RegularPaymentDTO> getRegularPaymentsByPayerFullName(String payerFullName) {
        Iterable<RegularPaymentDTO> regularPaymentDtoS = webClient.get()
                .uri("/regular-payments")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new ServiceErrorException("The service returned an error with status code 4xx. More details: status code " + error.statusCode().value() + " error URL " + error.request().getURI())))
                .bodyToFlux(RegularPaymentDTO.class)
                .filter(regularPaymentDTO -> regularPaymentDTO.getPayerFullName().equals(payerFullName))
                .toIterable();

        return StreamSupport.stream(regularPaymentDtoS.spliterator(), false)
                .toList();
    }

    public List<RegularPaymentDTO> getRegularPaymentsByRecipientFullName(String recipientFullName) {
        Iterable<RegularPaymentDTO> regularPaymentDtoS = webClient.get()
                .uri("/regular-payments")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new ServiceErrorException("The service returned an error with status code 4xx. More details: status code " + error.statusCode().value() + " error URL " + error.request().getURI())))
                .bodyToFlux(RegularPaymentDTO.class)
                .filter(regularPaymentDTO -> regularPaymentDTO.getRecipientName().equals(recipientFullName))
                .toIterable();

        return StreamSupport.stream(regularPaymentDtoS.spliterator(), false)
                .toList();
    }

    public List<WiringDTO> getPaymentWriteOffHistory(Long regularPaymentId) {
        Iterable<WiringDTO> wiringDtoS = webClient.get()
                .uri("/wiring")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new ServiceErrorException("The service returned an error with status code 4xx. More details: status code " + error.statusCode().value() + " error URL " + error.request().getURI())))
                .bodyToFlux(WiringDTO.class)
                .filter(wiringDTO -> wiringDTO.getPaymentInstructionsId().equals(regularPaymentId)
                        && new Timestamp(System.currentTimeMillis()).compareTo(Timestamp.valueOf(wiringDTO.getWiringTime())) == 1
                        && wiringDTO.getStatus().equals("A"))
                .toIterable();

        return StreamSupport.stream(wiringDtoS.spliterator(), true)
                .toList();
    }
}