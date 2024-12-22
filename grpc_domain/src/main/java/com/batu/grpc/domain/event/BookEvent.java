package com.batu.grpc.domain.event;

import com.batu.domain.event.DomainEvent;
import com.batu.grpc.domain.entity.Book;

import java.time.ZonedDateTime;

public abstract class BookEvent implements DomainEvent<Book> {
    private final Book book;
    private final ZonedDateTime createdAt;

    public BookEvent(Book book, ZonedDateTime createdAt) {
        this.book = book;
        this.createdAt = createdAt;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Book getBook() {
        return book;
    }
}
