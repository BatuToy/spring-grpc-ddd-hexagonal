package com.batu.grpc;

import com.batu.grpc.domain.AuthorDomainService;
import com.batu.grpc.domain.entity.Address;
import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.entity.Book;
import com.batu.grpc.domain.entity.Contact;
import com.batu.grpc.domain.event.AuthorCreatedEvent;
import com.batu.grpc.domain.exception.AddressDomainException;
import com.batu.grpc.domain.exception.AuthorDomainException;
import com.batu.grpc.domain.exception.BookDomainException;
import com.batu.grpc.domain.exception.ContactDomainException;
import com.batu.grpc.dto.author.create.CreateAuthorCommand;
import com.batu.grpc.mapper.author.AuthorDataMapper;
import com.batu.grpc.ports.output.repository.AddressRepository;
import com.batu.grpc.ports.output.repository.AuthorRepository;
import com.batu.grpc.ports.output.repository.BookRepository;
import com.batu.grpc.ports.output.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateAuthorCommandHelper {

    private final AuthorDataMapper authorDataMapper;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final ContactRepository contactRepository;
    private final AddressRepository addressRepository;
    private final AuthorDomainService authorDomainService;
    // New proxy for persisting author in to database without transactional problems!
    @Transactional
    public AuthorCreatedEvent persisAuthor(CreateAuthorCommand createAuthorCommand){
        Author author = authorDataMapper.createAuthorCommandToAuthor(createAuthorCommand);
        AuthorCreatedEvent authorCreatedEvent = authorDomainService.validateAndInitializeAuthor(author);
        log.info(" \n AuthorId= \n {} BookIds= {} \n AddressId= {} \n ContactId= {}",
                authorCreatedEvent.getAuthor().getId().getValue(),
                authorCreatedEvent.getAuthor().getAddress().getId().getValue(),
                authorCreatedEvent.getAuthor().getContact().getId().getValue());
        // Must save the relational entities before saving the author but this cause Transactional issues!
        saveAddress(authorCreatedEvent.getAuthor().getAddress());
        saveContact(authorCreatedEvent.getAuthor().getContact());
        saveAuthor(author);
        return authorCreatedEvent;
    }

    private void saveContact(Contact contact) {
        Contact contactResult = contactRepository.save(contact);
        if(contactResult == null){
            log.error("Could not save contact!");
            throw new ContactDomainException("Could not save contact!");
        }
        log.info("Contact with id= {} saved successfully!", contact.getId().getValue());
    }

    private void saveAddress(Address address) {
        Address addressResult = addressRepository.save(address);
        if (addressResult == null){
            log.error("Could not save address!");
            throw new AddressDomainException("Could not save address");
        }
        log.info("Address with id= {} saved successfully!", addressResult.getId().getValue());
    }

    // Because of there is no book yet in the persist store we can not save the Author!
    private void saveAuthor(Author author) {
        Author authorResult = authorRepository.save(author);
        if(authorResult == null){
            log.error("Could not save author!");
            throw new AuthorDomainException("Could not save the author!");
        }
        log.info("Author with id= {} saved successfully!", authorResult.getId().getValue());
    }

    private void saveAllBooks(List<Book> books, Author author){
        books.forEach(
                book -> {
                    Book bookResult = bookRepository.save(book, author);
                    if(bookResult == null){
                        log.error("Could not save book!");
                        throw new BookDomainException("Could not save book!");
                    }
                    log.info("Book with id= {} saved successfully!", bookResult.getId().getValue());
                }
        );
    }

}
