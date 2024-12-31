package com.batu.grpc.exception;

import java.io.IOException;
import java.io.InterruptedIOException;

public class ServerException extends  GrpcServerException {
    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
