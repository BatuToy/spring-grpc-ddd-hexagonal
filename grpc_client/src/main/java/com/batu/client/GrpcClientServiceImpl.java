package com.batu.client;

import com.batu.client.mapper.author.AuthorGrpcClientMapper;
import com.batu.client.mapper.book.BookGrpcClientMapper;
import com.batu.*;
import com.batu.CreateAuthorResponse;
import com.batu.TrackBookQuery;
import com.batu.grpc.dto.author.create.CreateAuthorCommand;
import com.batu.grpc.dto.author.track.TrackAuthorQuery;
import com.batu.grpc.dto.author.track.TrackAuthorResponse;
import com.batu.grpc.dto.book.create.CreateBookCommand;
import com.batu.grpc.dto.book.create.CreateBookResponse;
import com.batu.grpc.dto.book.track.TrackBookResponse;
import com.batu.grpc.dto.book.track.TrackBookStockQuery;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@Validated
public class GrpcClientServiceImpl implements GrpcClientService{

    @GrpcClient("grpc-client-channel")
    private final ApplicationServiceGrpcServerGrpc.ApplicationServiceGrpcServerBlockingStub blockingStub;
    private final AuthorGrpcClientMapper authorGrpcClientMapper;
    private final BookGrpcClientMapper bookGrpcClientMapper;


    public com.batu.grpc.dto.author.create.CreateAuthorResponse createAuthor(CreateAuthorCommand createAuthorCommand){
        com.batu.CreateAuthorCommand grpcCreateAuthorCommand = authorGrpcClientMapper.createAuthorCommandToGrpcCreateAuthorCommand(createAuthorCommand);
        log.info("(gRPC) \t Client making request for creating Author!");
        CreateAuthorResponse grpcResponse = blockingStub.createAuthor(grpcCreateAuthorCommand);
        log.info("(gRPC) \t Author was created successfully with author id= {}", UUID.fromString(grpcResponse.getAuthorId()));
        return authorGrpcClientMapper.grpcCreateAuthorResponseToCreateAuthorResponse(grpcResponse);
    }

    public com.batu.grpc.dto.author.track.TrackAuthorResponse trackAuthor(TrackAuthorQuery trackAuthorQuery){
        com.batu.TrackAuthorQuery grpcTrackAuthorQuery = authorGrpcClientMapper.trackAuthorQueryToGrpcTrackAuthorQuery(trackAuthorQuery);
        log.info("(gRPC) \t Author starting to tracking with tracking id= {}", UUID.fromString(grpcTrackAuthorQuery.getTrackingId()));
        com.batu.TrackAuthorResponse grpcResponse = blockingStub.trackAuthor(grpcTrackAuthorQuery);
        TrackAuthorResponse trackAuthorResponse = authorGrpcClientMapper.grpcTrackAuthorResponseToTrackAuthorResponse(grpcResponse);
        log.info("(gRPC) \t Author tracked successfully with author id= {}", trackAuthorResponse.getAuthorId());
        return trackAuthorResponse;
    }

    public CreateBookResponse createBook(CreateBookCommand createBookCommand){
        com.batu.CreateBookCommand grpcCreateBookCommand = bookGrpcClientMapper.createBookCommandToGrpcCreateBookCommand(createBookCommand);
        log.info("(gRPC) \t Book starting to creating for author id= {}", UUID.fromString(grpcCreateBookCommand.getAuthorId()));
        com.batu.CreateBookResponse grpcResponse = blockingStub.createBook(grpcCreateBookCommand);
        CreateBookResponse createBookResponse = bookGrpcClientMapper.grpcCreateBookResponseToCreateBookResponse(grpcResponse);
        log.info("(gRPC) \t Book was created successfully with book id= {}", createBookResponse.getBookId());
        return createBookResponse;
    }

    public TrackBookResponse trackBook(@Valid TrackBookStockQuery trackBookStockQuery){
        TrackBookQuery trackBookQuery = bookGrpcClientMapper.trackBookStockQueryToGrpcTrackBookQuery(trackBookStockQuery);
        log.info("(gRPC) \t Book starting to tracking with sku code= {}", trackBookStockQuery.getSkuCode().getValue());
        com.batu.TrackBookResponse grpcResponse = blockingStub.trackBook(trackBookQuery);
        TrackBookResponse trackBookResponse = bookGrpcClientMapper.grpcTrackBookResponseToTrackBookResponse(grpcResponse);
        log.info("(gRPC) \t Book was tracked successfully with sku code= {}", trackBookResponse.getSkuCode());
        return trackBookResponse;
    }

}
