package com.batu.grpc.mapper.author;

import com.batu.common.domain.valueobject.Money;
import com.batu.grpc.domain.entity.Address;
import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.entity.Book;
import com.batu.grpc.domain.entity.Contact;
import com.batu.grpc.dto.author.create.CreateAuthorCommand;
import com.batu.grpc.dto.author.create.CreateAuthorResponse;
import com.batu.grpc.dto.author.track.TrackAuthorResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorDataMapper {

    public Author createAuthorCommandToAuthor(CreateAuthorCommand createAuthorCommand){
        return Author.builder()
                .firstName(createAuthorCommand.getFirstName())
                .lastName(createAuthorCommand.getLastName())
                .salary( new Money(createAuthorCommand.getSalary()))
                .address(addressDtoToAddress(createAuthorCommand.getAddress()))
                .contact(contactDtoToContact(createAuthorCommand.getContact()))
                .gender(createAuthorCommand.getGender())
                .books(setBooksDtoToBook(createAuthorCommand.getBooks()))
                .build();
    }

    public CreateAuthorResponse authorToCreateAuthorResponse(Author author, String message){
        return CreateAuthorResponse.builder()
                .authorTrackingId(author.getTrackingId().getValue())
                .addressId(author.getAddress().getId().getValue())
                .contactId(author.getContact().getId().getValue())
                .message(message)
                .build();
    }

    public TrackAuthorResponse authorToTrackAuthorResponse(Author author, String message){
        return TrackAuthorResponse.builder()
                .authorId(author.getId().getValue())
                .addressId(author.getAddress().getId().getValue())
                .contactId(author.getAddress().getId().getValue())
                .message(message)
                .build();
    }

    private Address addressDtoToAddress(com.batu.grpc.dto.author.create.Address address){
        return new Address(
                address.getCity(),
                address.getStreetName(),
                address.getPostalCode()
        );
    }

    private Contact contactDtoToContact(com.batu.grpc.dto.author.create.Contact contact){
        return new Contact(
                contact.getEmail(),
                contact.getPhoneNumber()
        );
    }

    private List<Book> setBooksDtoToBook(List<com.batu.grpc.dto.author.create.Book> books){
        return books.stream().map(
                book -> Book.builder()
                        .price(new Money(book.getPrice().getAmount()))
                        .pageSize(book.getPageSize())
                        .bookStatus(book.getBookStatus())
                        .title(book.getTitle())
                        .build()
        ).toList();

    }
}
