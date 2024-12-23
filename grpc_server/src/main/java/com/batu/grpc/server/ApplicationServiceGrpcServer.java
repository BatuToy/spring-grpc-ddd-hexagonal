package com.batu.grpc.server;

import com.batu.grpc.*;
import com.batu.grpc.ApplicationServiceGrpcServerGrpc.ApplicationServiceGrpcServerImplBase;
import com.batu.grpc.CreateAuthorCommand;
import com.batu.grpc.CreateAuthorResponse;
import com.batu.grpc.CreateBookCommand;
import com.batu.grpc.CreateBookResponse;
import com.batu.grpc.TrackAuthorQuery;
import com.batu.grpc.TrackAuthorResponse;
import com.batu.grpc.TrackBookQuery;
import com.batu.grpc.TrackBookResponse;
import com.batu.grpc.exception.GrpcAuthorException;
import com.batu.grpc.mapper.author.AuthorGrpcServerMapper;
import com.batu.grpc.ports.input.service.ApplicationService;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@RequiredArgsConstructor
@GrpcService
public class ApplicationServiceGrpcServer extends ApplicationServiceGrpcServerGrpc.ApplicationServiceGrpcServerImplBase {

    private final ApplicationService applicationService;
    private final AuthorGrpcServerMapper authorGrpcMapper;

    @Override
    public void createAuthor(CreateAuthorCommand request, StreamObserver<CreateAuthorResponse> responseObserver) {
        try {
            com.batu.grpc.dto.author.create.CreateAuthorCommand createAuthorCommand = authorGrpcMapper.grpcCommandToCreateAuthorCommand(request);
            com.batu.grpc.dto.author.create.CreateAuthorResponse createAuthorResponse = applicationService.createAuthor(createAuthorCommand);
            CreateAuthorResponse grpcResponse = authorGrpcMapper.createAuthorResponseToGrpcCreateAuthorResponse(createAuthorResponse);
            responseObserver.onNext(grpcResponse);
            responseObserver.onCompleted();
        } catch (GrpcAuthorException exception){
            responseObserver.onError(new GrpcAuthorException("Error occur in the server!",Status.INTERNAL.withDescription("Error occur!").asRuntimeException()));
        }
    }

    @Override
    public void trackAuthor(TrackAuthorQuery request, StreamObserver<TrackAuthorResponse> responseObserver) {
        try {
            com.batu.grpc.dto.author.track.TrackAuthorQuery trackAuthorQuery =authorGrpcMapper.grpcTrackAuthorQueryToTrackAuthorQuery(request);
            com.batu.grpc.dto.author.track.TrackAuthorResponse trackAuthorResponse = applicationService.trackAuthor(trackAuthorQuery);
            TrackAuthorResponse grpcResponse = authorGrpcMapper.trackAuthorResponseToGrpcTrackAuthorResponse(trackAuthorResponse);
            responseObserver.onNext(grpcResponse);
            responseObserver.onCompleted();
        } catch (GrpcAuthorException e){
            responseObserver.onError(new GrpcAuthorException("Error occur in the server!",Status.INTERNAL.withDescription("Error occur!").asRuntimeException()));
        }
    }

    @Override
    public void createBook(CreateBookCommand request, StreamObserver<CreateBookResponse> responseObserver) {
        // map the request object to other request object.
    }

    @Override
    public void trackBook(TrackBookQuery request, StreamObserver<TrackBookResponse> responseObserver) {
        // map the request object to other request object.
    }
}
