package com.batu;

import com.batu.grpc.domain.AuthorDomainService;
import com.batu.grpc.domain.AuthorDomainServiceImpl;
import com.batu.grpc.ports.output.message.author.AuthorCreatedMessageRequestPublisher;
import com.batu.grpc.ports.output.repository.AuthorRepository;
import com.batu.grpc.ports.output.repository.BookRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.*;

@SpringBootApplication(scanBasePackages = "com.batu")
public class TestConfigurations {

    @Bean
    public AuthorCreatedMessageRequestPublisher authorCreatedMessageRequestPublisher(){
        return mock(AuthorCreatedMessageRequestPublisher.class);
    }

    @Bean
    public AuthorDomainService domainService(){
        return new AuthorDomainServiceImpl();
    }

    @Bean
    public AuthorRepository authorRepository(){
        return mock(AuthorRepository.class);
    }

    @Bean
    public BookRepository bookRepository(){
        return mock(BookRepository.class);
    }
}
