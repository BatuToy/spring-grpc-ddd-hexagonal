package com.batu.grpc.ports.output.repository;

import com.batu.grpc.domain.entity.Author;

import java.util.Optional;
import java.util.UUID;

public interface AuthorRepository {
    Author save(Author author);
    Optional<Author> findAuthorByTrackingId(UUID trackingId);
    Optional<Author> findAuthorByAuthorId(UUID authorId);
}
