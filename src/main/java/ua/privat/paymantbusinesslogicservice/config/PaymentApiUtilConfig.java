package ua.privat.paymantbusinesslogicservice.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import ua.privat.clientlib.utils.PaymentApiUtil;
import ua.privat.clientlib.utils.PaymentApiUtilI;

@Configuration
public class PaymentApiUtilConfig {

    @Bean
    public PaymentApiUtilI getPaymentApiUtil(@Qualifier("myWebClient") WebClient webClient) {
        return new PaymentApiUtil(webClient);
    }
}
