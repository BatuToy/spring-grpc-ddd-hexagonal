package com.batu.grpc.domain;

import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.event.AuthorCreatedEvent;

public interface AuthorDomainService {
    AuthorCreatedEvent validateAndInitializeAuthor(Author author);
}
