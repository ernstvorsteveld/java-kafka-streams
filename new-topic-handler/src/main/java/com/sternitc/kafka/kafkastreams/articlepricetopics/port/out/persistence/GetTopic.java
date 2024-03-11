package com.sternitc.kafka.kafkastreams.articlepricetopics.port.out.persistence;

import java.util.Collection;

public interface GetTopic extends TopicPersistence {

    Topic get(TopicName name);

    Collection<Topic> get(Page page);

    record Page(int start, int pageSize){}

}
