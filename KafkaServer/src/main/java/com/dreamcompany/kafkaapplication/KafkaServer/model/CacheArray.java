package com.dreamcompany.kafkaapplication.KafkaServer.model;

import java.util.concurrent.CopyOnWriteArrayList;


public class CacheArray {
    CopyOnWriteArrayList<String> cache;

    public CopyOnWriteArrayList<String> getCache() {
        return cache;
    }

    public void setCache(CopyOnWriteArrayList<String> cache) {
        this.cache = cache;
    }
}
