package com.sternitc.kafka.articleprice.application.domain.service;

import com.sternitc.kafka.articleprice.application.domain.model.ArticlePrice;

public interface ArticleNameHandler {

    void handle(ArticlePrice articlePrice);
}
