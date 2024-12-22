package com.batu.grpc.domain.exception;

import com.batu.domain.exception.DomainException;

public class BookDomainException extends DomainException {

    public BookDomainException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookDomainException(String message) {
        super(message);
    }
}
