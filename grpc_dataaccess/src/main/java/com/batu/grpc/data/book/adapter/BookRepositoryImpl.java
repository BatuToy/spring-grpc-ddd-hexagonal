package com.batu.grpc.data.book.adapter;

import com.batu.grpc.data.book.mapper.BookDataAccessMapper;
import com.batu.grpc.data.book.repo.BookJpaRepository;
import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.entity.Book;
import com.batu.grpc.dto.book.track.SkuCode;
import com.batu.grpc.ports.output.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class BookRepositoryImpl implements BookRepository {

    private final BookDataAccessMapper bookDataAccessMapper;
    private final BookJpaRepository bookJpaRepository;

    @Override
    public Book save(Book book, Author author) {
        return bookDataAccessMapper.
                bookEntityToBook
                        (bookJpaRepository
                                .save(bookDataAccessMapper.bookToBookEntity(book, author)));
    }

    @Override
    public Optional<Book> trackBook(SkuCode skuCode) {
        return bookJpaRepository
                .findBySkuCode(skuCode.getValue().toString())
                .map(bookDataAccessMapper::bookEntityToBook);
    }
}
