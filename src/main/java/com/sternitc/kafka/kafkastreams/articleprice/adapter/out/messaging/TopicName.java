package com.sternitc.kafka.kafkastreams.articleprice.adapter.out.messaging;

public class TopicName {

    public static final String TOPIC_PREFIX = "article_price_";

    private String id;
    private final String topicName;
    private final String articleName;

    public TopicName(String articleName) {
        if(articleName == null || articleName.isEmpty() || articleName.isBlank()) {
            throw new ArticleNameIsEmptyException();
        }
        this.topicName = newName(articleName);
        this.articleName = articleName;
    }

    public static String newName(String articleName) {
        return TOPIC_PREFIX + articleName.toLowerCase();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTopicName() {
        return topicName;
    }

    public String getArticleName() {
        return articleName;
    }
}
