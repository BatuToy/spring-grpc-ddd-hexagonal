package com.batu.client.mapper.author;

import com.batu.domain.valueobject.BookStatus;
import com.batu.Address;
import com.batu.Contact;
import com.batu.CreateAuthorCommand;
import com.batu.Gender;
import com.batu.grpc.dto.author.create.CreateAuthorResponse;
import com.batu.grpc.dto.author.track.TrackAuthorQuery;
import com.batu.grpc.dto.book.create.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class AuthorGrpcClientMapper {

    public CreateAuthorCommand createAuthorCommandToGrpcCreateAuthorCommand(com.batu.grpc.dto.author.create.CreateAuthorCommand createAuthorCommand){
        return CreateAuthorCommand.newBuilder()
                .setFirstName(createAuthorCommand.getFirstName())
                .setLastName(createAuthorCommand.getLastName())
                .setSalary(createAuthorCommand.getSalary().toString())
                .setAddress(addressToGrpcAddress(createAuthorCommand.getAddress()))
                .setContact(contactToGrpcContact(createAuthorCommand.getContact()))
                .addAllBooks(booksToGrpcBooks(createAuthorCommand.getBooks())) // which method should be right maybe null Pointer exception?
                .setGender(genderToGrpcGender(createAuthorCommand.getGender()))
                .build();
    }

    public com.batu.TrackAuthorQuery trackAuthorQueryToGrpcTrackAuthorQuery(TrackAuthorQuery trackAuthorQuery){
        return com.batu.TrackAuthorQuery.newBuilder()
                .setTrackingId(trackAuthorQuery.getTrackingId().toString())
                .build();
    }

    public com.batu.grpc.dto.author.track.TrackAuthorResponse grpcTrackAuthorResponseToTrackAuthorResponse(com.batu.TrackAuthorResponse trackAuthorResponse){
        return com.batu.grpc.dto.author.track.TrackAuthorResponse.builder()
                .authorId(UUID.fromString(trackAuthorResponse.getAuthorId()))
                .contactId(UUID.fromString(trackAuthorResponse.getContactId()))
                .addressId(UUID.fromString(trackAuthorResponse.getAddressId()))
                .message(trackAuthorResponse.getMessage())
                .build();
    }

    private List<com.batu.Book> booksToGrpcBooks(List<Book> books) {
        return books.stream().map(
                book -> com.batu.Book.newBuilder()
                        .setBookStatus(bookStatusToGrpcBookStatus(book.getBookStatus()))
                        .setPageSize(book.getPageSize())
                        .setPrice(book.getPrice().getAmount().toString())
                        .setTitle(book.getTitle())
                        .build()
        ).toList();
    }

    private Contact contactToGrpcContact(com.batu.grpc.dto.author.create.Contact contact) {
        return Contact.newBuilder()
                .setEmail(contact.getEmail())
                .setPhoneNumber(contact.getPhoneNumber())
                .build();
    }

    private Address addressToGrpcAddress(com.batu.grpc.dto.author.create.Address address) {
        return Address.newBuilder()
                .setCountry(address.getCountry())
                .setCity(address.getCity())
                .setStreet(address.getStreetName())
                .setPostalCode(address.getPostalCode())
                .build();
    }

    private Gender genderToGrpcGender(com.batu.domain.valueobject.Gender gender) {
        return Gender.valueOf(gender.name());
    }
    // Change this book status object to dto create object from the Application service.
    private com.batu.BookStatus bookStatusToGrpcBookStatus(BookStatus bookStatus){
        return com.batu.BookStatus.valueOf(bookStatus.name());
    }

    public CreateAuthorResponse grpcCreateAuthorResponseToCreateAuthorResponse(com.batu.CreateAuthorResponse createAuthorResponse){
        return CreateAuthorResponse.builder()
                .authorId(UUID.fromString(createAuthorResponse.getAuthorId()))
                .authorTrackingId(UUID.fromString(createAuthorResponse.getTrackingId()))
                .addressId(UUID.fromString(createAuthorResponse.getAddressId()))
                .contactId(UUID.fromString(createAuthorResponse.getContactId()))
                .message(createAuthorResponse.getMessage())
                .build();
    }
}
