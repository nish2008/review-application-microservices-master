package com.dreamcompany.kafkaapplication.KafkaServer.controller;

import com.dreamcompany.kafkaapplication.KafkaServer.model.CacheArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CopyOnWriteArrayList;

@RestController
public class KafkaController {

    @Autowired
    CacheArray cacheArray;

    @RequestMapping("readfromcache")
    public String readfromcache(){
        String stringPlate = new String();
        CopyOnWriteArrayList<String> msg = cacheArray.getCache();
        if(!msg.isEmpty()) {
            for (String s : msg) {
                stringPlate = stringPlate + "<br>" + s.toString();
                msg.remove(msg.indexOf(s));
            }

        }
        return stringPlate;
    }

}
