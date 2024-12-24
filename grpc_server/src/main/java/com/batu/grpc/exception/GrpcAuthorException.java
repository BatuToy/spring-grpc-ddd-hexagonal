package com.batu.grpc.exception;

import org.apache.catalina.users.GenericRole;

public class GrpcAuthorException extends GrpcServerException {
    public GrpcAuthorException(String message) {
        super(message);
    }

    public GrpcAuthorException(String message, Throwable cause) {
        super(message, cause);
    }
}
