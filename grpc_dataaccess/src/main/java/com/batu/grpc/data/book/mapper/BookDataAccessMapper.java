package com.batu.grpc.data.book.mapper;

import com.batu.common.domain.valueobject.Money;
import com.batu.grpc.data.author.mapper.AuthorDataAccessMapper;
import com.batu.grpc.data.book.entity.BookEntity;
import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.entity.Book;
import com.batu.grpc.domain.valueObject.AuthorId;
import com.batu.grpc.domain.valueObject.BookId;
import com.batu.grpc.domain.valueObject.SkuCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class BookDataAccessMapper {
    // There is no need for to set Author in the Book Entity cause already set in the AuthorMapper like:
    // author -> books -> setAuthor -> set Author for each Book coming from the Author Domain object.

    private final AuthorDataAccessMapper authorDataAccessMapper;
    public BookEntity bookToBookEntity(Book book, Author author){
         return BookEntity.builder()
                .id(book.getId().getValue())
                .price(book.getPrice().getAmount())
                .title(book.getTitle())
                .skuCode(book.getSkuCode().getValue().toString())
                .pageSize(book.getPageSize())
                 .bookStatus(book.getBookStatus())
                 .author(authorDataAccessMapper.authorToAuthorEntity(author))
                .build();
    }

    public Book bookEntityToBook(BookEntity bookEntity){
        return Book.builder()
                .bookId( new BookId(bookEntity.getId()))
                .authorId( new AuthorId(bookEntity.getAuthor().getId()))
                .skuCode( new SkuCode(UUID.fromString(bookEntity.getSkuCode())))
                .bookStatus(bookEntity.getBookStatus())
                .price( new Money(bookEntity.getPrice()))
                .title(bookEntity.getTitle())
                .build();
    }
}
