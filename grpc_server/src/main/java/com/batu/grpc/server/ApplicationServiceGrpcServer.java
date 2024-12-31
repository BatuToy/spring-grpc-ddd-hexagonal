package com.batu.grpc.server;

import com.batu.*;
import com.batu.CreateAuthorCommand;
import com.batu.CreateAuthorResponse;
import com.batu.CreateBookCommand;
import com.batu.CreateBookResponse;
import com.batu.TrackAuthorQuery;
import com.batu.TrackAuthorResponse;
import com.batu.TrackBookQuery;
import com.batu.TrackBookResponse;
import com.batu.grpc.dto.book.track.TrackBookStockQuery;
import com.batu.grpc.exception.GrpcAuthorException;
import com.batu.grpc.exception.GrpcBookException;
import com.batu.grpc.mapper.author.AuthorGrpcServerMapper;
import com.batu.grpc.mapper.book.BookGrpcServerMapper;
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
    private final AuthorGrpcServerMapper authorGrpcServerMapper;
    private final BookGrpcServerMapper bookGrpcServerMapper;

    @Override
    public void createAuthor(CreateAuthorCommand request, StreamObserver<CreateAuthorResponse> responseObserver) {
        try {
            com.batu.grpc.dto.author.create.CreateAuthorCommand createAuthorCommand = authorGrpcServerMapper.grpcCommandToCreateAuthorCommand(request);
            com.batu.grpc.dto.author.create.CreateAuthorResponse createAuthorResponse = applicationService.createAuthor(createAuthorCommand);
            CreateAuthorResponse grpcResponse = authorGrpcServerMapper.createAuthorResponseToGrpcCreateAuthorResponse(createAuthorResponse);
            responseObserver.onNext(grpcResponse);
            responseObserver.onCompleted();
        } catch (GrpcAuthorException exception){
            responseObserver.onError(new GrpcAuthorException("Error occur while creating the author in the server!",Status.INTERNAL.withDescription("Error occur!").asRuntimeException()));
        }
    }

    @Override
    public void trackAuthor(TrackAuthorQuery request, StreamObserver<TrackAuthorResponse> responseObserver) {
        try {
            com.batu.grpc.dto.author.track.TrackAuthorQuery trackAuthorQuery = authorGrpcServerMapper.grpcTrackAuthorQueryToTrackAuthorQuery(request);
            com.batu.grpc.dto.author.track.TrackAuthorResponse trackAuthorResponse = applicationService.trackAuthor(trackAuthorQuery);
            TrackAuthorResponse grpcResponse = authorGrpcServerMapper.trackAuthorResponseToGrpcTrackAuthorResponse(trackAuthorResponse);
            responseObserver.onNext(grpcResponse);
            responseObserver.onCompleted();
        } catch (GrpcAuthorException e){
            responseObserver.onError(new GrpcAuthorException("Error occur while tracking the author in the server!",Status.INTERNAL.withDescription("Error occur!").asRuntimeException()));
        }
    }

    @Override
    public void createBook(CreateBookCommand request, StreamObserver<CreateBookResponse> responseObserver) {
        try {
            com.batu.grpc.dto.book.create.CreateBookCommand createBookCommand = bookGrpcServerMapper.grpcCreateBookCommandToCreateBookCommand(request);
            com.batu.grpc.dto.book.create.CreateBookResponse createBookResponse = applicationService.createBook(createBookCommand);
            CreateBookResponse grpcResponse = bookGrpcServerMapper.createBookResponseToGrpcCreateBookResponse(createBookResponse);
            responseObserver.onNext(grpcResponse);
            responseObserver.onCompleted();
        } catch (GrpcBookException e) {
            responseObserver.onError(new GrpcBookException("Error occur while creating the book in the server!", Status.CANCELLED.withDescription("Error occur in the server!").asRuntimeException()));
        }
    }

    @Override
    public void trackBook(TrackBookQuery request, StreamObserver<TrackBookResponse> responseObserver) {
        try {
            TrackBookStockQuery trackBookStockQuery = bookGrpcServerMapper.grpcTrackBookQueryToTrackBookStockQuery(request);
            com.batu.grpc.dto.book.track.TrackBookResponse trackBookResponse = applicationService.trackBook(trackBookStockQuery);
            TrackBookResponse grpcResponse = bookGrpcServerMapper.trackBookResponseToGrpcTrackBookResponse(trackBookResponse);
            responseObserver.onNext(grpcResponse);
            responseObserver.onCompleted();
        } catch (GrpcBookException e){
            responseObserver.onError(new GrpcBookException("Error occur while tracking the book in the server!", Status.INTERNAL.withDescription("Error occur in the server!").asRuntimeException()));
        }
    }
}
