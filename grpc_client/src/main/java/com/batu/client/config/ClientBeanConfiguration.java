package com.batu.client.config;

import com.batu.client.config.data.GrpcConfigData;
import com.batu.ApplicationServiceGrpcServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: batu_toy
 */

@Configuration
@RequiredArgsConstructor
public class ClientBeanConfiguration {
    private final GrpcConfigData grpcConfigData;

    //Todo: How can i go define a TLS/SSL communication between client-server?

    @Bean
    public ApplicationServiceGrpcServerGrpc.ApplicationServiceGrpcServerBlockingStub blockingStub(){
        ManagedChannel channel = ManagedChannelBuilder.
                forAddress(grpcConfigData.getHostName(), grpcConfigData.getPort())
                .usePlaintext()
                .build();
        return ApplicationServiceGrpcServerGrpc.newBlockingStub(channel);
    }
}
