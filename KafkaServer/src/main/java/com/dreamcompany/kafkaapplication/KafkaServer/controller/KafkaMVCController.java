package com.dreamcompany.kafkaapplication.KafkaServer.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class KafkaMVCController {

    @GetMapping("/ajax")
    public String performAjax(Model model){
        return "ajax";
    }
}
