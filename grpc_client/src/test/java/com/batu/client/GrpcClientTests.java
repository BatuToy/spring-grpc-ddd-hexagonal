package com.batu.client;

import com.batu.client.rest.author.AuthorGrpcController;
import com.batu.client.rest.book.BookGrpcController;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ClientTestConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GrpcClientTests {
    @Autowired
    private GrpcClientServiceImpl grpcClientService;
    @Autowired
    private AuthorGrpcController authorGrpcController;
    @Autowired
    private BookGrpcController bookGrpcController;

}
