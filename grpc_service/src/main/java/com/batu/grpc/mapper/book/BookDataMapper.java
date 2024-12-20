package com.batu.grpc.mapper.book;

import com.batu.common.domain.valueobject.Money;
import com.batu.grpc.domain.entity.Book;
import com.batu.grpc.domain.valueObject.AuthorId;
import com.batu.grpc.dto.book.create.CreateBookCommand;
import com.batu.grpc.dto.book.create.CreateBookResponse;
import org.springframework.stereotype.Component;

@Component
public class BookDataMapper {

    public Book createBookCommandToBook(CreateBookCommand createBookCommand){
        return Book.builder()
                // BookId and SkuCode and BookStatus will be generating in the Domain class.
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
                .authorId(book.getId().getValue())
                .skuCode(book.getSkuCode().getValue())
                .bookStatus(book.getBookStatus())
                .message(message)
                .build();
    }
}
