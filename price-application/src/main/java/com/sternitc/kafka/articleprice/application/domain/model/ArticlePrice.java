package com.sternitc.kafka.articleprice.application.domain.model;

import com.sternitc.kafka.articleprice.application.domain.service.ArticleNameIsEmptyException;

public class ArticlePrice {

    private final String name;
    private int price;

    public ArticlePrice(String name, int price) {
        if ((name == null) || name.isBlank() || name.isEmpty()) {
            throw new ArticleNameIsEmptyException();
        }
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return price;
    }
}
