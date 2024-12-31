package com.batu.kafka.config.data;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka-config")
@Data
public class KafkaConfigData {
    private String bootstrapServer;
    private String keyDeserializerClass;
    private String valueDeserializerClass;
    private String acks;
}
