package com.sternitc.kafka.articleprice.application.port.out.messaging;

public interface NewArticlePricePublisherPort {

    void publish(NewArticlePriceEvent event);

    record NewArticlePriceEvent(String name, int price) {
    }

}
