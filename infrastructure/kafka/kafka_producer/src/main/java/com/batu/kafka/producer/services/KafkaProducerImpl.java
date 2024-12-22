package com.batu.kafka.producer.services;

import com.batu.kafka.producer.exception.KafkaPublisherException;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RequiredArgsConstructor
public class KafkaProducerImpl<K extends java.lang.String, V> implements KafkaProducer<K, V>{

    private final KafkaTemplate<K, V> kafkaTemplate;

    @Override
    public void send(String topicName, K key, V message, Integer partition) {
        CompletableFuture<SendResult<K, V>> result = kafkaTemplate.send(topicName, key, message);
        result.whenCompleteAsync((res, err) -> {
            ProducerRecord<K, V> rec = res.getProducerRecord();
            RecordMetadata data = res.getRecordMetadata();
            if(err == null){
                log.info("");
            } else {
                log.error("");
                //throw new KafkaProducerException(rec, "", new Throwable(""));
                throw new KafkaPublisherException("");
            }
        });
    }

    @PreDestroy
    public void onClose(){
        if(kafkaTemplate != null){
            kafkaTemplate.destroy();
        }
    }
}
