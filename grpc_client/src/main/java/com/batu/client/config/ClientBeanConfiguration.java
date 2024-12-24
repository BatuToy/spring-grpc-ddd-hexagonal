package com.batu.client.config;

import com.batu.client.config.data.GrpcConfigData;
import com.batu.grpc.ApplicationServiceGrpcServerGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class ClientBeanConfiguration {

    private final GrpcConfigData grpcConfigData;

   @Bean
    public ApplicationServiceGrpcServerGrpc.ApplicationServiceGrpcServerBlockingStub blockingStub(){
       ManagedChannel channel = ManagedChannelBuilder
               .forAddress(grpcConfigData.getHostName(), grpcConfigData.getPort())
               .usePlaintext()
               .idleTimeout(60, TimeUnit.SECONDS)
               .build();
        return ApplicationServiceGrpcServerGrpc.newBlockingStub(channel);
   }
}
