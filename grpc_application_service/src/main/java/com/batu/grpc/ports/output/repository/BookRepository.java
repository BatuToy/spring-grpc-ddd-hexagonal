package com.batu.grpc.ports.output.repository;

import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.entity.Book;
import com.batu.grpc.dto.book.track.SkuCode;

import java.util.Optional;

public interface BookRepository {
    Book save(Book book, Author author);
    Optional<Book> trackBook(SkuCode skuCode);
}
