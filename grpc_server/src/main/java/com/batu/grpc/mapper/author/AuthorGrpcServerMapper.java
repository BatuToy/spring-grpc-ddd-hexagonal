package com.batu.grpc.mapper.author;

import com.batu.domain.valueobject.Money;
import com.batu.BookStatus;
import com.batu.CreateAuthorCommand;
import com.batu.CreateAuthorResponse;
import com.batu.Gender;
import com.batu.grpc.dto.author.create.Address;
import com.batu.grpc.dto.author.create.Contact;
import com.batu.grpc.dto.author.track.TrackAuthorQuery;
import com.batu.grpc.dto.author.track.TrackAuthorResponse;
import com.batu.grpc.dto.book.create.Book;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class AuthorGrpcServerMapper {

    public com.batu.grpc.dto.author.create.CreateAuthorCommand grpcCommandToCreateAuthorCommand(CreateAuthorCommand createAuthorCommand){
        return com.batu.grpc.dto.author.create.CreateAuthorCommand.builder()
                .firstName(createAuthorCommand.getFirstName())
                .lastName(createAuthorCommand.getLastName())
                .salary(new BigDecimal(createAuthorCommand.getSalary()))
                .gender(grpGenderToGenderDto(createAuthorCommand.getGender()))
                .address(grpcAddressToAddress(createAuthorCommand.getAddress()))
                .contact(grpcContactToContact(createAuthorCommand.getContact()))
                .books(grpcBooksToBooks(createAuthorCommand.getBooksList()))
                .build();
    }

    public CreateAuthorResponse createAuthorResponseToGrpcCreateAuthorResponse(com.batu.grpc.dto.author.create.CreateAuthorResponse createAuthorResponse){
        return CreateAuthorResponse.newBuilder()
                .setAuthorId(createAuthorResponse.getAuthorId().toString())
                .setTrackingId(createAuthorResponse.getAuthorTrackingId().toString())
                .setAddressId(createAuthorResponse.getAddressId().toString())
                .setContactId(createAuthorResponse.getContactId().toString())
                .setMessage(createAuthorResponse.getMessage())
                .build();
    }

   public TrackAuthorQuery grpcTrackAuthorQueryToTrackAuthorQuery(com.batu.TrackAuthorQuery trackAuthorQuery){
        return TrackAuthorQuery.builder()
                .trackingId(UUID.fromString(trackAuthorQuery.getTrackingId()))
                .build();
   }

   public com.batu.TrackAuthorResponse trackAuthorResponseToGrpcTrackAuthorResponse(TrackAuthorResponse trackAuthorResponse){
        return com.batu.TrackAuthorResponse.newBuilder()
                .setAuthorId(trackAuthorResponse.getAuthorId().toString())
                .setAddressId(trackAuthorResponse.getAddressId().toString())
                .setContactId(trackAuthorResponse.getContactId().toString())
                .setMessage(trackAuthorResponse.getMessage())
                .build();
   }

    private List<Book> grpcBooksToBooks(List<com.batu.Book> books){
        return books.stream().map(
                book -> Book.builder()
                        .title(book.getTitle())
                        .price( new Money(new BigDecimal(book.getPrice())))
                        .pageSize(book.getPageSize())
                        .bookStatus(grpcBookStatusToBookStatusDto(book.getBookStatus()))
                        .build()
        ).toList();
    }

    private Address grpcAddressToAddress(com.batu.Address address){
       return Address.builder()
               .country(address.getCountry())
               .city(address.getCity())
               .streetName(address.getStreet())
               .postalCode(address.getPostalCode())
               .build();
    }

    private Contact grpcContactToContact(com.batu.Contact contact){
        return Contact.builder()
                .email(contact.getEmail())
                .phoneNumber(contact.getPhoneNumber())
                .build();
    }

    private com.batu.domain.valueobject.Gender grpGenderToGenderDto(Gender gender){
        return com.batu.domain.valueobject.Gender.valueOf(gender.name());
    }

    private com.batu.domain.valueobject.BookStatus grpcBookStatusToBookStatusDto(BookStatus bookStatus){
        return com.batu.domain.valueobject.BookStatus.valueOf(bookStatus.name());
    }
}
