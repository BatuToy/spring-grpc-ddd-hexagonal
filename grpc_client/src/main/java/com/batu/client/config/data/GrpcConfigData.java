package com.batu.client.config.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "grpc-stub")
@Data
public class GrpcConfigData {
    private String hostName;
    private Integer port;
}
