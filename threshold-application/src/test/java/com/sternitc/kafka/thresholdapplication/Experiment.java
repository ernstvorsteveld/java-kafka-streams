package com.sternitc.kafka.thresholdapplication;

import io.confluent.ksql.api.client.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Experiment {

    public static String KSQLDB_SERVER_HOST = "0.0.0.0";
    public static int KSQLDB_SERVER_HOST_PORT = 8088;

    private final Client client = create();

    @BeforeEach
    public void setup_db() {
        String sql = "CREATE STREAM userprofile (userid INT, firstname VARCHAR, lastname VARCHAR, countrycode VARCHAR, rating DOUBLE) WITH (VALUE_FORMAT = 'JSON', KAFKA_TOPIC = 'USERPROFILE');";
        CompletableFuture<ExecuteStatementResult> result = client.executeStatement(sql);
    }

    @Test
    public void should_get_users_profiles() throws ExecutionException, InterruptedException {
        Map<String, Object> properties = Collections.singletonMap(
                "auto.offset.reset", "earliest"
        );

        String sql = "select firstname, lastname, countrycode, rating from USERPROFILE emit changes;";
        StreamedQueryResult streamedQueryResult = client.streamQuery(sql, properties).get();

        for (int i = 0; i < 10; i++) {
            // Block until a new row is available
            Row row = streamedQueryResult.poll();
            if (row != null) {
                UserProfile userProfile = new UserProfile(
                        row.getString("FIRSTNAME"),
                        row.getString("LASTNAME"),
                        row.getString("COUNTRYCODE"),
                        row.getDouble("RATING"));
                System.out.println("Row: " + userProfile);
            } else {
                System.out.println("Query has ended.");
            }
        }
    }

    record UserProfile(String firstName, String lastName, String countryCode, Double rating) {
    }

    private Client create() {
        ClientOptions options = ClientOptions.create()
                .setHost(KSQLDB_SERVER_HOST)
                .setPort(KSQLDB_SERVER_HOST_PORT)
                .setUseTls(false)
                .setUseAlpn(true);
        return Client.create(options);
    }

}