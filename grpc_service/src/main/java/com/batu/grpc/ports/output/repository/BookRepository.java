package com.batu.grpc.ports.output.repository;

import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.entity.Book;

public interface BookRepository {
    Book save(Book book, Author author);
}
