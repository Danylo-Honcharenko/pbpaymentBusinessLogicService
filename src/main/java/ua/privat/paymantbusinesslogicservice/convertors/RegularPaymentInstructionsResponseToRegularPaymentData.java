package ua.privat.paymantbusinesslogicservice.convertors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import ua.privat.clientlib.http.response.RegularPaymentInstructionsResponse;
import ua.privat.clientlib.http.response.data.RegularPaymentData;

@Component
public class RegularPaymentInstructionsResponseToRegularPaymentData implements Converter<RegularPaymentInstructionsResponse, RegularPaymentData> {

    @Override
    public RegularPaymentData convert(RegularPaymentInstructionsResponse source) {
        Assert.notNull(source,
                "The source must not be null");
        return RegularPaymentData.builder()
                .id(source.getId())
                .payerid(source.getPayerid())
                .recipientid(source.getRecipientid())
                .writeOffPeriod(source.getWriteOffPeriod())
                .writeoffdate(source.getWriteoffdate())
                .paymentAmount(source.getPaymentAmount())
                .state(source.getState())
                .build();
    }
}
