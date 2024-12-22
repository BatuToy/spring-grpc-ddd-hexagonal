package com.batu.grpc.data.author.mapper;

import com.batu.domain.valueobject.Money;
import com.batu.grpc.data.author.entity.AddressEntity;
import com.batu.grpc.data.author.entity.AuthorEntity;
import com.batu.grpc.data.author.entity.ContactEntity;
import com.batu.grpc.data.book.entity.BookEntity;
import com.batu.grpc.domain.entity.Address;
import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.entity.Book;
import com.batu.grpc.domain.entity.Contact;
import com.batu.grpc.domain.valueObject.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AuthorDataAccessMapper {

    public AuthorEntity authorToAuthorEntity(Author author){
        AuthorEntity authorEntity =  AuthorEntity.builder()
                .id(author.getId().getValue())
                .trackingId(author.getTrackingId().getValue())
                .firstName(author.getFirstName())
                .lastName(author.getLastName())
                .gender(author.getGender())
                .address(addressToAddressEntity(author.getAddress()))
                .contact(contactToContactEntity(author.getContact()))
                .books(booksToBookEntities(author.getBooks()))
                .salary(author.getSalary().getAmount())
                .build();
        authorEntity.getAddress().setAuthor(authorEntity);
        authorEntity.getContact().setAuthor(authorEntity);
        authorEntity.getBooks().forEach( bookEntity -> bookEntity.setAuthor(authorEntity));
        return authorEntity;
    }

    public Author authorEntityToAuthor(AuthorEntity authorEntity){
        return Author.builder()
                .id( new AuthorId(authorEntity.getId()))
                .trackingId(new TrackingId(authorEntity.getTrackingId()))
                .firstName(authorEntity.getFirstName())
                .lastName(authorEntity.getLastName())
                .gender(authorEntity.getGender())
                .contact(contactEntityToContact(authorEntity.getContact()))
                .address(addressEntityToAddress(authorEntity.getAddress()))
                .books(bookEntitiesToBook(authorEntity.getBooks()))
                .salary( new Money(authorEntity.getSalary()))
                .build();
    }

    public AddressEntity addressToAddressEntity(Address address){
        // AuthorId or Author will be set from the AuthorEntity!
        return AddressEntity.builder()
                .id(address.getId().getValue())
                .country(address.getCountry())
                .city(address.getCity())
                .street(address.getStreetName())
                .postalCode(address.getPostalCode())
                .build();
    }

    public ContactEntity contactToContactEntity(Contact contact){
        // AuthorId or Author  will be set from the AuthorEntity!
        return ContactEntity.builder()
                .id(contact.getId().getValue())
                .email(contact.getEmail())
                .phoneNumber(contact.getPhoneNumber())
                .build();
    }

    private List<BookEntity> booksToBookEntities(List<Book> books){
        return books.stream().map(
                book -> BookEntity.builder()
                        .id(book.getId().getValue())
                        .price(book.getPrice().getAmount())
                        .title(book.getTitle())
                        .skuCode(book.getSkuCode().getValue().toString())
                        .pageSize(book.getPageSize())
                        .build()
        ).toList();
    }

    private List<Book> bookEntitiesToBook(List<BookEntity> bookEntities){
        return bookEntities.stream().map(
                bookEntity -> Book.builder()
                        .bookId( new BookId(bookEntity.getId()))
                        .authorId( new AuthorId(bookEntity.getAuthor().getId()))
                        .skuCode( new SkuCode(UUID.fromString(bookEntity.getSkuCode())))
                        .title(bookEntity.getTitle())
                        .pageSize(bookEntity.getPageSize())
                        .price( new Money(bookEntity.getPrice()))
                        .build()
        ).toList();
    }

    public Address addressEntityToAddress(AddressEntity addressEntity){
        return new Address(
            new AddressId(addressEntity.getId()),
                addressEntity.getCountry(),
                addressEntity.getCity(),
                addressEntity.getStreet(),
                addressEntity.getPostalCode()
        );
    }

    public Contact contactEntityToContact(ContactEntity contactEntity){
        return new Contact(
          new ContactId(contactEntity.getId()),
                contactEntity.getEmail(),
                contactEntity.getPhoneNumber()
        );
    }
}
