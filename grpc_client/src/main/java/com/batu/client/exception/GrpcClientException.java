package com.batu.client.exception;

import com.batu.application.exception.ApplicationException;

public class GrpcClientException extends ApplicationException {
    public GrpcClientException(String message) {
        super(message);
    }

    public GrpcClientException(String message, Throwable cause) {
        super(message, cause);
    }
}
