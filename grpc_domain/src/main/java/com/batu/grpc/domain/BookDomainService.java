package com.batu.grpc.domain;

import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.entity.Book;
import com.batu.grpc.domain.event.BookCreatedEvent;

public interface BookDomainService {
    BookCreatedEvent validateAndInitializeBook(Book book, Author author);
}
