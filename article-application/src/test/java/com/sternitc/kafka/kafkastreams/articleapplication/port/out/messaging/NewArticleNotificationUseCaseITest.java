package com.sternitc.kafka.kafkastreams.articleapplication.port.out.messaging;

import com.sternitc.kafka.kafkastreams.ArticleApplication;
import com.sternitc.kafka.kafkastreams.articleapplication.application.domain.model.NewArticleBoundaryCommand;
import com.sternitc.kafka.kafkastreams.articleapplication.application.port.in.NewArticleNotificationUseCase;
import com.sternitc.kafka.kafkastreams.articleapplication.application.port.out.persistence.GetArticlePriceBoundarySpecification;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@SpringBootApplication(
        scanBasePackageClasses = ArticleApplication.class,
        exclude = {
                MongoAutoConfiguration.class,
                MongoDataAutoConfiguration.class
        })
@ActiveProfiles({"no-mongodb", "kafka"})
class NewArticleNotificationUseCaseITest {

    @Autowired
    private NewArticleNotificationUseCase newArticleNotificationUseCase;

    @Autowired
    private GetArticlePriceBoundarySpecification getArticlePriceBoundarySpecification;

    @Autowired
    private Map<String, Map<String, Object>> eventMap;

    @Test
    public void should_store_and_publish_new_article_boundary() throws InterruptedException {
        NewArticleBoundaryCommand command = new NewArticleBoundaryCommand("10", "UPPER", 10);
        newArticleNotificationUseCase.accept(command);

        assertThat(getArticlePriceBoundarySpecification.get("10").value()).isEqualTo(10);

        TimeUnit.SECONDS.sleep(1);
        assertThat(eventMap.size()).isEqualTo(1);
    }
}