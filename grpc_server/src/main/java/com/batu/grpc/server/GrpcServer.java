package com.batu.grpc.server;

import com.batu.grpc.exception.GrpcBookException;
import com.batu.grpc.exception.GrpcServerException;
import com.batu.grpc.exception.ServerException;
import com.batu.grpc.mapper.author.AuthorGrpcServerMapper;
import com.batu.grpc.mapper.book.BookGrpcServerMapper;
import com.batu.grpc.ports.input.service.ApplicationService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InterruptedIOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class GrpcServer {

    @Value("${grpc.port}")
    private Integer grpcPort;

    private final ApplicationService applicationService;
    private final AuthorGrpcServerMapper authorGrpcServerMapper;
    private final BookGrpcServerMapper bookGrpcServerMapper;

    @PostConstruct
    public void startServer() {
        new Thread(() -> {
            try {
                Server server = ServerBuilder.forPort(grpcPort)
                        .addService(new ApplicationServiceGrpcServer(applicationService, authorGrpcServerMapper, bookGrpcServerMapper))
                        .build();

                server.start();
                log.info("Grpc server started successfully with port= {}", server.getPort());
                server.awaitTermination();
                // Maybe take this out?
                server.shutdown();
            } catch (Exception exc) {
                log.error(exc.getMessage(), exc);
                throw new ServerException("Grpc server exception= " + exc.getLocalizedMessage());
            }
        }).start();
    }


}
