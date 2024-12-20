package com.batu.grpc.domain.event;

import com.batu.grpc.domain.entity.Book;

import java.time.ZonedDateTime;

public class BookCreatedEvent extends BookEvent{
    public BookCreatedEvent(Book book, ZonedDateTime createdAt) {
        super(book, createdAt);
    }
}
