package com.batu.grpc.servers;

import com.batu.grpc.*;
import com.batu.grpc.CreateBookResponse;
import io.grpc.stub.StreamObserver;

public class BookServiceServer extends BookServiceGrpc.BookServiceImplBase {

    @Override
    public void createBook(com.batu.grpc.CreateBookRequest request, StreamObserver<CreateBookResponse> responseObserver) {
        super.createBook(request, responseObserver);
    }

    @Override
    public void getBookBySkuCode(com.batu.grpc.GetBookBySkuCodeRequest request, StreamObserver<com.batu.grpc.GetBookBySkuCodeResponse> responseObserver) {
        super.getBookBySkuCode(request, responseObserver);
    }
}
