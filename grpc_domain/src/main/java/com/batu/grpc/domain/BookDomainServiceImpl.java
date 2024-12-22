package com.batu.grpc.domain;

import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.entity.Book;
import com.batu.grpc.domain.event.BookCreatedEvent;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.batu.domain.DomainConstants.*;
@Slf4j
public class BookDomainServiceImpl implements BookDomainService{

    @Override
    public BookCreatedEvent validateAndInitializeBook(Book book, Author author) {
        book.validateBook();
        book.initializeBook(author.getId());
        initializeBookInToAuthor(book, author);
        return new BookCreatedEvent(book, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    private void initializeBookInToAuthor(Book book, Author author){
        author.initializeBookToAuthor(book);
    }

}
