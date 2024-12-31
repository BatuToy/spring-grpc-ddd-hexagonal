package com.batu.grpc;

import com.batu.grpc.domain.AuthorDomainService;
import com.batu.grpc.domain.AuthorDomainServiceImpl;
import com.batu.grpc.domain.BookDomainService;
import com.batu.grpc.domain.BookDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public AuthorDomainService authorDomainService(){
        return new AuthorDomainServiceImpl();
    }

    @Bean
    public BookDomainService bookDomainService(){
        return new BookDomainServiceImpl();
    }
}
