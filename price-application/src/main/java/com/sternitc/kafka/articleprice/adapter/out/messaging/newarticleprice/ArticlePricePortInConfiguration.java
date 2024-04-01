package com.sternitc.kafka.articleprice.adapter.out.messaging.newarticleprice;

import com.sternitc.kafka.articleprice.application.domain.service.ArticlePriceMapper;
import com.sternitc.kafka.articleprice.application.domain.service.NewArticlePricesService;
import com.sternitc.kafka.articleprice.application.port.in.NewArticlePricesUseCase;
import com.sternitc.kafka.articleprice.application.port.out.messaging.NewArticlePricePublisherPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticlePricePortInConfiguration {

    @Bean
    public NewArticlePricesUseCase newArticlePricesUseCase(
            ArticlePriceMapper mapper,
            NewArticlePricePublisherPort newArticlePricePublisherPort) {
        return new NewArticlePricesService(mapper, newArticlePricePublisherPort);
    }

}
