package com.sternitc.kafka.kafkastreams.articleprice.adapter.out.persistence.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "stats")
public class MongoTopicEntity {

    @Id
    private String id;

    private String topicName;

    private String articleName;

    private MongoTopicEntity() {
        topicName = "";
        articleName = "";
    }
    public MongoTopicEntity(String name, String articleName) {
        this.topicName = name;
        this.articleName = articleName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }
}
