package com.sternitc.kafka.kafkastreams.boundaryapplication.port.out.messaging;

import io.confluent.ksql.api.client.Client;
import io.confluent.ksql.api.client.ClientOptions;

public class KsqlDBClientBuilder {

    private boolean useTls = false;
    private String host = "localhost";
    private int port = 8088;

    public KsqlDBClientBuilder(boolean useTls, String host, int port) {
        this.useTls = useTls;
        this.host = host;
        this.port = port;
    }

    public KsqlDBClientBuilder() {
    }

    public KsqlDBClientBuilder useTls(boolean useTls) {
        this.useTls = useTls;
        return this;
    }

    public KsqlDBClientBuilder host(String host) {
        this.host = host;
        return this;
    }

    public KsqlDBClientBuilder port(int port) {
        this.port = port;
        return this;
    }

    public Client build() {
        ClientOptions options = ClientOptions.create()
                .setUseTls(useTls)
                .setHost(host)
                .setPort(port)
                .setUseAlpn(true);
        return Client.create(options);
    }
}
