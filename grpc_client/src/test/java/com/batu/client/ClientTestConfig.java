package com.batu.client;

import com.batu.client.rest.author.AuthorGrpcController;
import com.batu.client.rest.book.BookGrpcController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class ClientTestConfig {

    @Bean
    public GrpcClientServiceImpl grpcClientService(){
        return mock(GrpcClientServiceImpl.class);
    }

    @Bean
    public BookGrpcController bookGrpcController(){
        return mock(BookGrpcController.class);
    }
    @Bean
    public AuthorGrpcController authorGrpcController(){
        return mock(AuthorGrpcController.class);
    }
}
