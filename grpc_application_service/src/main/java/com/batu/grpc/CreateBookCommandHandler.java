package com.batu.grpc;

import com.batu.grpc.domain.entity.Book;
import com.batu.grpc.domain.event.BookCreatedEvent;
import com.batu.grpc.dto.book.create.CreateBookCommand;
import com.batu.grpc.dto.book.create.CreateBookResponse;
import com.batu.grpc.mapper.book.BookDataMapper;
import com.batu.grpc.ports.output.message.book.BookCreatedMessageRequestPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateBookCommandHandler {

    private final BookDataMapper bookDataMapper;
    private final CreateBookCommandHelper createBookCommandHelper;
    //private final BookCreatedMessageRequestPublisher bookCreatedMessageRequestPublisher;

    public CreateBookResponse createBook(CreateBookCommand createBookCommand) {
        Book book = bookDataMapper.createBookCommandToBook(createBookCommand);
        BookCreatedEvent bookCreatedEvent = createBookCommandHelper.persistBook(book);
        log.info("Book created successfully with book id= {}", bookCreatedEvent.getBook().getId().getValue());
        //bookCreatedMessageRequestPublisher.publish(bookCreatedEvent);
        log.info("BookCreatedEvent published successfully with book id= {}", bookCreatedEvent.getBook().getId().getValue());
        return bookDataMapper.bookToCreateBookResponse(
                bookCreatedEvent.getBook(),
                "Book created successfully with author id= " + bookCreatedEvent.getBook().getAuthorId().getValue()
                        + " with sku code= " + bookCreatedEvent.getBook().getSkuCode().getValue()
        );
    }
}
