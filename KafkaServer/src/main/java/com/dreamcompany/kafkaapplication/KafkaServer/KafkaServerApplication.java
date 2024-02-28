package com.dreamcompany.kafkaapplication.KafkaServer;

import com.dreamcompany.kafkaapplication.KafkaServer.model.CacheArray;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

import java.util.concurrent.CopyOnWriteArrayList;

@SpringBootApplication
@EnableDiscoveryClient
public class KafkaServerApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(KafkaServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(KafkaServerApplication.class, args);
	}

	@Bean
	public NewTopic getLogDiaryTopic(){
		return TopicBuilder.name("logdiary")
				.partitions(2)
				.replicas(2)
				.build();
	}

	@Bean
	public CacheArray getCacheArray(){
		CacheArray cacheArray = new CacheArray();
		cacheArray.setCache(new CopyOnWriteArrayList<String>());
		return cacheArray;
	}
}
