package com.batu.client.service;

import com.batu.client.mapper.AuthorGrpcClientMapper;
import com.batu.grpc.*;
import com.batu.grpc.CreateAuthorResponse;
import com.batu.grpc.TrackAuthorResponse;
import com.batu.grpc.dto.author.create.CreateAuthorCommand;
import com.batu.grpc.dto.author.track.TrackAuthorQuery;
import com.batu.grpc.dto.book.create.CreateBookResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class GrpcClientService {

    @GrpcClient("grpc-client-channel")
    private final ApplicationServiceGrpcServerGrpc.ApplicationServiceGrpcServerBlockingStub blockingStub;
    private final AuthorGrpcClientMapper authorGrpcClientMapper;


    public com.batu.grpc.dto.author.create.CreateAuthorResponse createAuthor(CreateAuthorCommand createAuthorCommand){
        com.batu.grpc.CreateAuthorCommand grpcCreateAuthorCommand = authorGrpcClientMapper.createAuthorCommandToGrpcCreateAuthorCommand(createAuthorCommand);
        log.info("(gRPC) \t Client making request for creating Author!");
        CreateAuthorResponse grpcResponse = blockingStub.createAuthor(grpcCreateAuthorCommand);
        log.info("(gRPC) \t Author was created successfully with author id= {}", UUID.fromString(grpcResponse.getAuthorId()));
        return authorGrpcClientMapper.grpcCreateAuthorResponseToCreateAuthorResponse(grpcResponse);
    }

    public com.batu.grpc.dto.author.track.TrackAuthorResponse trackAuthor(TrackAuthorQuery trackAuthorQuery){
        com.batu.grpc.TrackAuthorQuery grpcTrackAuthorQuery = authorGrpcClientMapper.trackAuthorQueryToGrpcTrackAuthorQuery(trackAuthorQuery);
        log.info("(gRPC) \t Author starting to tracking with tracking id= {}", trackAuthorQuery.getTrackingId());
        com.batu.grpc.TrackAuthorResponse grpcResponse = blockingStub.trackAuthor(grpcTrackAuthorQuery);
        log.info("(gRPC) \t Author tracked successfully with author id= {}", grpcResponse.getAuthorId());
        return authorGrpcClientMapper.grpcTrackAuthorResponseToTrackAuthorResponse(grpcResponse);
    }

}
