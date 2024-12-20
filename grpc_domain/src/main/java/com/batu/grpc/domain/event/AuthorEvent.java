package com.batu.grpc.domain.event;

import com.batu.common.domain.event.DomainEvent;
import com.batu.grpc.domain.entity.Author;

import java.time.ZonedDateTime;

public abstract class AuthorEvent implements DomainEvent<Author> {
    private final Author author;
    private final ZonedDateTime createdAt;

    public AuthorEvent(Author author, ZonedDateTime createdAt) {
        this.author = author;
        this.createdAt = createdAt;
    }

    public Author getAuthor() {
        return author;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}
