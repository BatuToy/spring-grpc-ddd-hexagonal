package com.batu.grpc.mapper.book;

import com.batu.domain.valueobject.Money;
import com.batu.grpc.domain.entity.Book;
import com.batu.grpc.domain.valueObject.AuthorId;
import com.batu.grpc.dto.book.create.CreateBookCommand;
import com.batu.grpc.dto.book.create.CreateBookResponse;
import com.batu.grpc.dto.book.track.TrackBookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookDataMapper {

    public Book createBookCommandToBook(CreateBookCommand createBookCommand){
        return Book.builder()
                .authorId( new AuthorId(createBookCommand.getAuthorId()))
                .bookStatus(createBookCommand.getBookStatus())
                .pageSize(createBookCommand.getPageSize())
                .price( new Money(createBookCommand.getPrice()))
                .title(createBookCommand.getTitle())
                .build();
    }

    public CreateBookResponse bookToCreateBookResponse(Book book, String message){
        return CreateBookResponse.builder()
                .bookId(book.getId().getValue())
                .authorId(book.getAuthorId().getValue())
                .skuCode(book.getSkuCode().getValue())
                .bookStatus(book.getBookStatus())
                .message(message)
                .build();
    }

    public TrackBookResponse bookToTrackBookResponse(Book book, String message){
        return TrackBookResponse.builder()
                .bookId(book.getId().getValue())
                .authorId(book.getAuthorId().getValue())
                .skuCode(book.getSkuCode().getValue())
                .bookStatus(book.getBookStatus())
                .message(message)
                .build();
    }
}
