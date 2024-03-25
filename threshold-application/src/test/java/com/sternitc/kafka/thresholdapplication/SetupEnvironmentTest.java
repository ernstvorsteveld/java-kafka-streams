package com.sternitc.kafka.thresholdapplication;

import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ExecuteStatementResult;
import io.confluent.ksql.api.client.StreamInfo;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicDescription;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SetupEnvironmentTest {

    public static final String THRESHOLDS_TOPICS = "thresholds";
    public static final String ARTICLE_1_PRICES_TOPIC = "article1-prices-topic";
    private static KafkaAdmin kafkaAdmin;
    private static Client client;

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


        String sql = "CREATE STREAM article1_prices_stream (userid INT, firstname VARCHAR, lastname VARCHAR, countrycode VARCHAR, rating DOUBLE) WITH (VALUE_FORMAT = 'JSON', KAFKA_TOPIC = 'article1-prices-topic');";
        client.executeStatement(sql);

        CompletableFuture<List<StreamInfo>> listCompletableFuture = client.listStreams();
        Iterator<StreamInfo> iterator = listCompletableFuture.get().iterator();
        boolean found = false;
        while(iterator.hasNext() && !found) {
            StreamInfo next = iterator.next();
            if(next.getName().equalsIgnoreCase("article1_prices_stream")) {
                found = true;
            }
        }
        assertThat(found).isEqualTo(true);
    }

    @AfterAll
    public static void cleanUp() {
        String sql = "DROP STREAM IF EXISTS article1_prices_stream;";
        client.executeStatement(sql);

        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
        adminClient.deleteTopics(Arrays.stream(new String[]{THRESHOLDS_TOPICS, ARTICLE_1_PRICES_TOPIC}).toList());
    }
}
