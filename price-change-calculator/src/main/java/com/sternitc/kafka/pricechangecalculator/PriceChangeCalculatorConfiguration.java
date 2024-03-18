package com.sternitc.kafka.pricechangecalculator;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.CleanupConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.apache.kafka.streams.StreamsConfig.*;

@Configuration
public class PriceChangeCalculatorConfiguration {

    @Bean
    public StoreBuilder<KeyValueStore<String, ArticlePrice>> articlePricesStoreBuilder() {
        Map<String, Object> serdeProps = new HashMap<>();
        final Serializer<ArticlePrice> articlePriceSerializer = new JsonPOJOSerializer<>();
        serdeProps.put("JsonPOJOClass", ArticlePrice.class);
        articlePriceSerializer.configure(serdeProps, false);

        final Deserializer<ArticlePrice> articlePriceDeserializer = new JsonPOJODeserializer<>();
        serdeProps.put("JsonPOJOClass", ArticlePrice.class);
        articlePriceDeserializer.configure(serdeProps, false);

        return Stores.keyValueStoreBuilder(
                Stores.persistentKeyValueStore("old-new-prices"),
                Serdes.String(),
                Serdes.serdeFrom(articlePriceSerializer, articlePriceDeserializer)
        );
    }

    /**
     * ProcessNewPrices receives ArticlePrice objects.
     * <p>
     * First it updates the articlePricesStore,
     * where it sets the old and new prices for the article in the store.
     * <p>
     * Then it calculates the type of price change (Increasing or Decreasing) and publishes
     * that as an ArticlePriceChange.
     */
    @Bean
    public Function<KStream<String, ArticlePrice>, KStream<String, ArticlePriceChange>> processNewPrices() {
        return input -> input
                .process(ArticlePriceProcessor::new, "old-new-prices");
    }

    @Bean
    @Profile("test")
    public CleanupConfig cleanupConfig() {
        return new CleanupConfig(true, true);
    }
}
