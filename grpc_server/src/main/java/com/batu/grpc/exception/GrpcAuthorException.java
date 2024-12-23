package com.batu.grpc.exception;

public class GrpcAuthorException extends RuntimeException{
    public GrpcAuthorException(String message) {
        super(message);
    }

    public GrpcAuthorException(String message, Throwable cause) {
        super(message, cause);
    }
}
