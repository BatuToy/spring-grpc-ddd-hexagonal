package com.batu.kafka.producer.services;

public interface KafkaProducer<K extends java.lang.String, V extends java.lang.Object> {
    void send(String topicName, K key, V message, Integer partition);
}
