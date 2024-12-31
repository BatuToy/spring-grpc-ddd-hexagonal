package com.batu.grpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.batu")
@EntityScan(basePackages = "com.batu.grpc.data")
@EnableJpaRepositories(basePackages = {"com.batu.grpc.data"})
@EnableDiscoveryClient
public class GrpcServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcServiceApplication.class, args);
    }
}
