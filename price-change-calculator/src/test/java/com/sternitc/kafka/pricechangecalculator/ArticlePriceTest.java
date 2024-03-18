package com.sternitc.kafka.pricechangecalculator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ArticlePriceTest {

    @Test
    public void should_marshall() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        assertThat(objectMapper.writeValueAsString(
                new ArticlePrice("10", 10))).isEqualTo("{\"articleId\":\"10\",\"price\":10}");
    }

}