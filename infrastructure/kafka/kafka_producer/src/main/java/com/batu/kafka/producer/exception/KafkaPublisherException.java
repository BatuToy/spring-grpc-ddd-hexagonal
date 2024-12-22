package com.batu.kafka.producer.exception;

public class KafkaPublisherException extends RuntimeException{
    public KafkaPublisherException(String message) {
        super(message);
    }

    public KafkaPublisherException(String message, Throwable cause) {
        super(message, cause);
    }
}
