package com.sternitc.kafka.thresholdapplication;

import io.confluent.ksql.api.client.Client;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SetupEnvironmentTest {

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
    public void should_setup_base_environment() {
        NewTopic thresholds = TopicBuilder.name("thresholds")
                .partitions(1)
                .replicas(3)
                .compact()
                .build();

        NewTopic article1Prices = TopicBuilder.name("article1-prices-topic")
                .partitions(1)
                .replicas(3)
                .compact()
                .build();

        kafkaAdmin.createOrModifyTopics(thresholds, article1Prices);


//        String sql = "CREATE STREAM userprofilestream (userid INT, firstname VARCHAR, lastname VARCHAR, countrycode VARCHAR, rating DOUBLE) WITH (VALUE_FORMAT = 'JSON', KAFKA_TOPIC = 'USERPROFILE');";

    }

    @AfterAll
    public static void cleanUp() {
        AdminClient adminClient = AdminClient.create(kafkaAdmin.getConfigurationProperties());
        adminClient.deleteTopics(Arrays.stream(new String[]{"thresholds", "article1-prices-topic"}).toList());
    }
}
