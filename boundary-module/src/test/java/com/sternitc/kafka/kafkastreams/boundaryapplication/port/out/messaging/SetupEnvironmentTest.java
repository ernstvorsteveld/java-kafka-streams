package com.sternitc.kafka.kafkastreams.pricethresholdapplication.port.out.messaging;

import com.sternitc.kafka.kafkastreams.pricethresholdapplication.application.domain.model.ThresholdType;
import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.StreamInfo;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

public class SetupEnvironmentTest {

    public static final String THRESHOLDS_TOPICS = "thresholds-topic";
    public static final String ARTICLE_1_PRICES_TOPIC = "article1-prices-topic";
    private static KafkaAdmin kafkaAdmin;
    private static Client client;
    private static KafkaTemplate<String, ArticlePrice> articlePriceTemplate;
    private static KafkaTemplate<String, ArticleThreshold> articleThresholdTemplate;

    @BeforeAll
    public static void createKafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9192,localhost:9292,localhost:9392");
        kafkaAdmin = new KafkaAdmin(configs);
    }

    @BeforeAll
    public static void createKsqlDBClient() {
        client = new KsqlDBClientBuilder().build();
    }

    @BeforeAll
    public static void createKafkaTemplates() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                "localhost:9192,localhost:9292,localhost:9392");
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        ProducerFactory<String, ArticlePrice> articlePriceProducerFactory = new DefaultKafkaProducerFactory<>(configProps);
        articlePriceTemplate = new KafkaTemplate<>(articlePriceProducerFactory);
        ProducerFactory<String, ArticleThreshold> articleThresholdProducerFactory = new DefaultKafkaProducerFactory<>(configProps);
        articleThresholdTemplate = new KafkaTemplate<>(articleThresholdProducerFactory);
    }

    @Test
    public void should_setup_base_environment() throws ExecutionException, InterruptedException {
        NewTopic thresholds = TopicBuilder.name(THRESHOLDS_TOPICS)
                .partitions(1)
                .replicas(3)
                .compact()
                .build();

        NewTopic article1Prices = TopicBuilder.name(ARTICLE_1_PRICES_TOPIC)
                .partitions(1)
                .replicas(3)
                .compact()
                .build();

        kafkaAdmin.createOrModifyTopics(thresholds, article1Prices);

        Map<String, TopicDescription> topicsMap = kafkaAdmin.describeTopics(THRESHOLDS_TOPICS, ARTICLE_1_PRICES_TOPIC);
        assertThat(topicsMap.size()).isEqualTo(2);


        String sql = "CREATE STREAM IF NOT EXISTS article1_prices_stream (articleId INT, price INT) WITH (VALUE_FORMAT = 'JSON', KAFKA_TOPIC = 'article1-prices-topic');";
        client.executeStatement(sql);
        sql = "CREATE TABLE IF NOT EXISTS thresholds_table ( id BIGINT PRIMARY KEY, usertimestamp BIGINT, companyId VARCHAR, threshold INT ) WITH ( KAFKA_TOPIC = 'thresholds-topic', VALUE_FORMAT = 'JSON' );";
        client.executeStatement(sql);

        CompletableFuture<List<StreamInfo>> listCompletableFuture = client.listStreams();
        Iterator<StreamInfo> iterator = listCompletableFuture.get().iterator();
        boolean found = false;
        while (iterator.hasNext() && !found) {
            StreamInfo next = iterator.next();
            if (next.getName().equalsIgnoreCase("article1_prices_stream")) {
                found = true;
            }
        }
        assertThat(found).isEqualTo(true);

        articlePriceTemplate.send(ARTICLE_1_PRICES_TOPIC, "20", new ArticlePrice("20", ThresholdType.INCREASE.name(), 20));
        articleThresholdTemplate.send(THRESHOLDS_TOPICS, "1", new ArticleThreshold("10", 100, "ABOVE"));
    }

    //    @AfterAll
    public static void cleanUp() {
        String sql = "DROP STREAM IF EXISTS article1_prices_stream;";
        client.executeStatement(sql);
        sql = "DROP TABLE IF EXISTS thresholds_table;";
        client.executeStatement(sql);

        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
        adminClient.deleteTopics(Arrays.stream(new String[]{THRESHOLDS_TOPICS, ARTICLE_1_PRICES_TOPIC}).toList());
    }
}
