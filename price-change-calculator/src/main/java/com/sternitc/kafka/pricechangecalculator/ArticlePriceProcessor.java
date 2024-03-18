package com.sternitc.kafka.pricechangecalculator;

import org.apache.kafka.streams.processor.api.Processor;
import org.apache.kafka.streams.processor.api.ProcessorContext;
import org.apache.kafka.streams.processor.api.Record;
import org.apache.kafka.streams.state.KeyValueStore;

import java.time.Instant;

public class ArticlePriceProcessor implements Processor<String, ArticlePrice, String, ArticlePriceChange> {

    private KeyValueStore<String, ArticlePrice> oldNewPriceStore;
    private ProcessorContext<String, ArticlePriceChange> context;

    @Override
    public void init(ProcessorContext<String, ArticlePriceChange> context) {
        this.context = context;
        oldNewPriceStore = context.getStateStore("old-new-prices");
    }

    @Override
    public void process(Record<String, ArticlePrice> record) {
        ArticlePriceChange articlePriceChange = getArticlePriceChange(record);
        oldNewPriceStore.put(record.key(), record.value());
        context.forward(
                new Record<>(record.key(), articlePriceChange, Instant.now().toEpochMilli(), record.headers()));
    }

    private ArticlePriceChange getArticlePriceChange(Record<String, ArticlePrice> record) {
        ArticlePrice oldArticlePrice = oldNewPriceStore.get(record.key());
        return (oldArticlePrice == null) ?
                new ArticlePriceChange(record.key(), record.value(), record.value(), priceChangeType(record.value(), record.value())) :
                new ArticlePriceChange(record.key(), oldArticlePrice, record.value(), priceChangeType(oldArticlePrice, record.value()));
    }

    private PriceChangeType priceChangeType(ArticlePrice old, ArticlePrice current) {
        if (old.price() == current.price()) {
            return PriceChangeType.SAME;
        }
        return (old.price() < current.price()) ? PriceChangeType.INCREASED : PriceChangeType.DECREASED;
    }

    @Override
    public void close() {
        Processor.super.close();
    }

}

