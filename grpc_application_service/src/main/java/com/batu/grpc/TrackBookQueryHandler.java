package com.batu.grpc;

import com.batu.grpc.domain.entity.Book;
import com.batu.grpc.domain.exception.BookDomainException;
import com.batu.grpc.dto.book.track.TrackBookResponse;
import com.batu.grpc.dto.book.track.TrackBookStockQuery;
import com.batu.grpc.mapper.book.BookDataMapper;
import com.batu.grpc.ports.output.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class TrackBookQueryHandler {

    private final BookRepository bookRepository;
    private final BookDataMapper bookDataMapper;

    // Make this tracking system combined with (inventory service) which will be created for another module.
    // skuCode can go to other service with an event and check the inventory of the specific book.

    public TrackBookResponse trackBook(TrackBookStockQuery trackBookStockQuery) {
        Optional<Book> book = bookRepository.trackBook(trackBookStockQuery.getSkuCode());
        if(book.isEmpty()){
            log.error("Could not find the book with sku code= {}", trackBookStockQuery.getSkuCode().getValue());
            throw new BookDomainException("Could not find the book with sku code= " + trackBookStockQuery.getSkuCode().getValue());
        }
        return bookDataMapper.bookToTrackBookResponse(
                book.get(),
                "Book tracked with sku code= "
                        + book.get().getSkuCode().getValue() + "\n"
                        + "and book id= "
                        + book.get().getId().getValue()
        );
    }
}
