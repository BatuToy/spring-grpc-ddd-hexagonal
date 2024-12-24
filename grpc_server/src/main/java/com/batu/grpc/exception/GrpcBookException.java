package com.batu.grpc.exception;

public class GrpcBookException extends GrpcServerException{
    public GrpcBookException(String message) {
        super(message);
    }

    public GrpcBookException(String message, Throwable cause) {
        super(message, cause);
    }
}
