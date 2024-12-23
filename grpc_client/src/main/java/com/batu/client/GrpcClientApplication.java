package com.batu.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication(scanBasePackages = "com.batu.client")
//@EntityScan(basePackages = "com.batu.grpc.data")
//@EnableJpaRepositories(basePackages = "com.batu.grpc")
@SpringBootApplication
public class GrpcClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrpcClientApplication.class);
    }
}
