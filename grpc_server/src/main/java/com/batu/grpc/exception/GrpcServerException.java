package com.batu.grpc.exception;

public abstract class GrpcServerException extends RuntimeException{
    public GrpcServerException(String message) {
        super(message);
    }

    public GrpcServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
