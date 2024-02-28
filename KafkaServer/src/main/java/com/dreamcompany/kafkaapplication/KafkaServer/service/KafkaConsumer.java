package com.dreamcompany.kafkaapplication.KafkaServer.service;

import com.dreamcompany.kafkaapplication.KafkaServer.model.CacheArray;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class KafkaConsumer {

    @Autowired
    CacheArray cacheArray;

    private final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "logdiary",
            groupId = "group-id")
    public void consume(String data){
        CopyOnWriteArrayList<String> cache =  cacheArray.getCache();
        if (StringUtils.isNotEmpty(data)) {
            cache.add(data.toString());
        }
        LOG.info(String.format("Message received -> %s", data));
    }
}
