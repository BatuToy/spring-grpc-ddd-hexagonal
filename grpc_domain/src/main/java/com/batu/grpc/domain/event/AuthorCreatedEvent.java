package com.batu.grpc.domain.event;

import com.batu.grpc.domain.entity.Author;

import java.time.ZonedDateTime;

public class AuthorCreatedEvent extends AuthorEvent{
    public AuthorCreatedEvent(Author author, ZonedDateTime createdAt) {
        super(author, createdAt);
    }
}
