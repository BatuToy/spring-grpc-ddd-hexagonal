package com.batu.kafka.producer.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig<K extends java.lang.String, V extends Object> {

    @Bean
    public KafkaTemplate<K, V> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    private Map<String, Object> producerConfig(){
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "");
        props.put(ProducerConfig.ACKS_CONFIG, "1");
        // props will come here
        return props;
    }

    @Bean
    public DefaultKafkaProducerFactory<K, V> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerConfig());
    }
}
