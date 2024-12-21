package com.batu.grpc.servers;

import com.batu.grpc.CreateAddressRequest;
import com.batu.grpc.CreateAddressResponse;
import com.batu.grpc.CreateAuthorRequest;
import com.batu.grpc.CreateAuthorResponse;
import com.batu.grpc.CreateContactRequest;
import com.batu.grpc.CreateContactResponse;
import com.batu.grpc.GetAuthorByIdRequest;
import com.batu.grpc.GetAuthorByIdResponse;
import com.batu.grpc.domain.valueObject.AuthorId;
import com.batu.grpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
public class AuthorServiceServer extends AuthorServiceGrpc.AuthorServiceImplBase {

    @Override
    public void createAuthor(CreateAuthorRequest request, StreamObserver<CreateAuthorResponse> responseObserver) {
        super.createAuthor(request, responseObserver);
    }

    @Override
    public void createContact(CreateContactRequest request, StreamObserver<CreateContactResponse> responseObserver) {
        AuthorId authorId = new AuthorId(UUID.fromString(request.getContact().getAuthorId()));
        //applicationService.createContact(request.getContact());
        // We can split the services in here for the crud base problems. There is a big business logic will be come in here!
        //.updateAuthorAddress(authorId);
        super.createContact(request, responseObserver);
    }

    @Override
    public void createAddress(CreateAddressRequest request, StreamObserver<CreateAddressResponse> responseObserver) {
        super.createAddress(request, responseObserver);
    }

    @Override
    public void getAuthor(GetAuthorByIdRequest request, StreamObserver<GetAuthorByIdResponse> responseObserver) {
        super.getAuthor(request, responseObserver);
    }

}
