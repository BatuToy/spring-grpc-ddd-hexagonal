package com.batu.grpc;

import com.batu.grpc.domain.BookDomainService;
import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.entity.Book;
import com.batu.grpc.domain.event.BookCreatedEvent;
import com.batu.grpc.domain.exception.AuthorDomainException;
import com.batu.grpc.domain.exception.BookDomainException;
import com.batu.grpc.domain.valueObject.AuthorId;
import com.batu.grpc.exception.AuthorApplicationException;
import com.batu.grpc.exception.BookApplicationException;
import com.batu.grpc.mapper.book.BookDataMapper;
import com.batu.grpc.ports.output.repository.AuthorRepository;
import com.batu.grpc.ports.output.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Slf4j
@Component
@RequiredArgsConstructor
public class CreateBookCommandHelper {

    private final BookDataMapper bookDataMapper;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookDomainService bookDomainService;

    @Transactional
    public BookCreatedEvent persistBook(Book book){
        Author author = findAuthorByAuthorId(book.getAuthorId());
        BookCreatedEvent bookCreatedEvent = bookDomainService.validateAndInitializeBook(book, author);
        saveBook(bookCreatedEvent.getBook(), author);
        updateAuthor(author);
        return bookCreatedEvent;
    }

    private Author findAuthorByAuthorId(AuthorId authorId){
        Optional<Author> authorResult = authorRepository.findAuthorByAuthorId(authorId.getValue());
        if(authorResult.isEmpty()){
            log.error("Author with author id= {} not found in the persist store!", authorId.getValue());
            throw new AuthorApplicationException("Author with author id= " + authorId.getValue() + " not found in the persist store!");
        }
        log.info("Author with author id= {} successfully found in the persist store!", authorResult.get().getId().getValue());
        return authorResult.get();
    }

    private void saveBook(Book book, Author author){
        Book bookResult = bookRepository.save(book,author);
        if(bookResult == null){
            log.error("Could not save book!");
            throw new BookApplicationException("Could not save book!");
        }
        log.info("Book saved successfully with id= {} in to persist store!", bookResult.getId().getValue());
    }

    private void updateAuthor(Author author){
        Author authorResult = authorRepository.save(author);
        if(authorResult == null){
            log.error("Could not update author with author id= {}", author.getId().getValue());
            throw new AuthorApplicationException("Could not update author with author id= " + author.getId().getValue());
        }
        log.info(" \n Author updated successfully with author id= {} \n with books= {}"
                , authorResult.getId().getValue()
                , authorResult.getBooks().stream().map( book -> book.getId().getValue()).toList());
    }
}
