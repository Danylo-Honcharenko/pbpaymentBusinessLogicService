package ua.privat.paymantbusinesslogicservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ua.privat.clientlib.http.RegularPaymentInstructionsDataClient;
import ua.privat.clientlib.http.WiringDataClient;

/**
 * Конфигурация клиентов для отправки запросов
 */
@Configuration
public class PaymentApiUtilConfig {
    // URL
    private final String url = "http://localhost:8080/api";

    /**
     * Получить конфигурацию WebClient
     *
     * @return WebClient конфигурация
     */
    private WebClient getWebClientConfig() {
        return WebClient.builder()
                .baseUrl(this.url)
                .build();
    }

    /**
     * Получить клиент для отправки запросов
     *
     * @return RegularPaymentInstructionsDataClient клиент для отправки запросов
     */
    @Bean
    public RegularPaymentInstructionsDataClient getRegularPaymentInstructionsClient() {
        return new RegularPaymentInstructionsDataClient(this.getWebClientConfig());
    }

    /**
     * Получить клиент для отправки запросов
     *
     * @return RegularPaymentInstructionsDataClient клиент для отправки запросов
     */
    @Bean
    public WiringDataClient getWiringClient() {
        return new WiringDataClient(this.getWebClientConfig());
    }
}
