package com.batu.grpc;

import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.batu")
@EntityScan(basePackages = "com.batu.grpc.data")
@EnableJpaRepositories(basePackages = {"com.batu.grpc.data"})
public class GrpcServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcServiceApplication.class, args);
    }
}
