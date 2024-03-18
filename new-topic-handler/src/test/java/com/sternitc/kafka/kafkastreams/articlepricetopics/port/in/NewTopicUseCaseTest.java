package com.sternitc.kafka.kafkastreams.articlepricetopics.port.in;

import com.sternitc.kafka.kafkastreams.articlepricetopics.NewTopicApplication;
import com.sternitc.kafka.kafkastreams.articlepricetopics.port.out.persistence.GetTopic;
import com.sternitc.kafka.kafkastreams.articlepricetopics.port.out.persistence.TopicPersistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;

@SpringBootTest
@SpringBootApplication(
        scanBasePackageClasses = NewTopicApplication.class
)
//        exclude = {
//                MongoAutoConfiguration.class,
//                MongoDataAutoConfiguration.class
//        })
@ActiveProfiles({"no-mongodb", "kafka"})
public class NewTopicUseCaseTest {

    @Autowired
    private GetTopic getTopic;

    @Test
    public void should_listen_for_new_article_topics() {
        while (true) {
            Collection<TopicPersistence.Topic> topics = getTopic.get(new GetTopic.Page(0, 100));
            topics.forEach(t -> System.out.println(t.name()));
        }
    }

}