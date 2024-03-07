package com.sternitc.kafka.kafkastreams.articleprice.application.domain.model;

public class ArticlePrice {

    private final String id;
    private int price;

    public ArticlePrice(String id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return this.id;
    }

    public static class ArticlePriceBuilder {

        private String name;
        private int price;

        public ArticlePriceBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ArticlePriceBuilder price(int price) {
            this.price = price;
            return this;
        }

        public ArticlePrice build() {
            ArticlePrice ap = new ArticlePrice(name);
            ap.setPrice(price);
            return ap;
        }
    }
}
