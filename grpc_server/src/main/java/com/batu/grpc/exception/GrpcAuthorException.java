package com.batu.grpc.exception;

public class GrpAuthorException extends RuntimeException{
    public GrpAuthorException(String message) {
        super(message);
    }

    public GrpAuthorException(String message, Throwable cause) {
        super(message, cause);
    }
}
