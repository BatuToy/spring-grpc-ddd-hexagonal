package com.batu.kafka.producer.services;

import java.io.Serializable;

public interface KafkaProducer<K extends Serializable, V extends java.lang.Object> {
    void send(String topicName, K key, V message, Integer partition);
}
