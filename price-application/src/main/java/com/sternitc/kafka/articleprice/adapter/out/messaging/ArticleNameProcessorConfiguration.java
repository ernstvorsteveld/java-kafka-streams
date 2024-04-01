package com.sternitc.kafka.articleprice.adapter.out.messaging;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.CleanupConfig;

import java.util.function.Function;

@Configuration
public class ArticleNameProcessorConfiguration {

//    @Bean
//    public StoreBuilder<KeyValueStore<String, String>> articlePricesStoreBuilder() {
//        StreamsBuilder builder = new StreamsBuilder();
//        KeyValueBytesStoreSupplier storeSupplier =
//                Stores.inMemoryKeyValueStore("article_names");
//        return Stores.keyValueStoreBuilder(
//                Stores.persistentKeyValueStore("article_names"),
//                Serdes.String(),
//                Serdes.String()
//        );
//    }
//
//    /**
//     * ProcessNewPrices receives ArticlePrice objects.
//     * <p>
//     * First it updates the articlePricesStore,
//     * where it sets the old and new prices for the article in the store.
//     * <p>
//     * Then it calculates the type of price change (Increasing or Decreasing) and publishes
//     * that as an ArticlePriceChange.
//     */
//    @Bean
//    public Function<KStream<String, String>, KStream<String, ArticlePriceChange>> processNewPrices() {
//        return input -> input
//                .process(ArticlePriceProcessor::new, "old-new-prices");
//    }
//
//    @Bean
//    @Profile("test")
//    public CleanupConfig cleanupConfig() {
//        return new CleanupConfig(true, true);
//    }
}
