package com.batu.grpc.domain.entity;

import com.batu.common.domain.entity.BaseEntity;
import com.batu.common.domain.valueobject.Money;
import com.batu.grpc.domain.exception.AuthorDomainException;
import com.batu.grpc.domain.exception.BookDomainException;
import com.batu.grpc.domain.valueObject.AuthorId;
import com.batu.common.domain.valueobject.Gender;
import com.batu.grpc.domain.valueObject.TrackingId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Author extends BaseEntity<AuthorId> {
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final Money salary;

    private List<Book> books;
    private TrackingId trackingId;
    private Contact contact;
    private Address address;
    private Boolean isActive;

    public Author(String firstName, String lastName, Gender gender, Money salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.salary = salary;
    }

    public void initializeAuthor(){
        super.setId(new AuthorId(UUID.randomUUID()));
        this.trackingId = new TrackingId(UUID.randomUUID());
        initializeAddress();
        initializeContact();
        this.isActive = true;
    }

    public void validateAuthor(){
        validateId();
        validateSalary();
    }
    // Todo: Waste of cache need a solution!
    public void initializeBookToAuthor(Book book) {
        List<Book> booksMutable = new ArrayList<>();
        booksMutable.add(book);
        this.books = List.copyOf(booksMutable);
    }

    private void initializeAddress(){
        address.initializeWithoutAddressIdToAuthor(super.getId());
    }

    private void initializeContact(){
        contact.initializeWithoutContactIdToAuthor(super.getId());
    }


    private void validateSalary(){
        if(!salary.isGreaterThenZero() || this.salary.getAmount() == null){
            throw new BookDomainException("Book with id= " + super.getId().getValue() +" can not be null or lower or equal to zero!");
        }
    }

    private void validateId(){
        if(super.getId() != null){
            throw new AuthorDomainException("Author with id= " + super.getId().getValue() + " was already initialized to Author object!");
        }
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public Money getSalary() {
        return salary;
    }

    public List<Book> getBooks() {
        return books;
    }

    public TrackingId getTrackingId() {
        return trackingId;
    }

    public Contact getContact() {
        return contact;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isActive() {
        return isActive;
    }

    private Author(Builder builder) {
        super.setId(builder.authorId);
        trackingId = builder.trackingId;
        firstName = builder.firstName;
        lastName = builder.lastName;
        gender = builder.gender;
        salary = builder.salary;
        books = builder.books;
        contact = builder.contact;
        address = builder.address;
        isActive = builder.isActive;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private AuthorId authorId;
        private TrackingId trackingId;
        private String firstName;
        private String lastName;
        private Gender gender;
        private Money salary;
        private List<Book> books;
        private Contact contact;
        private Address address;
        private Boolean isActive;

        private Builder() {
        }

        public Builder id(AuthorId val) {
            authorId = val;
            return this;
        }

        public Builder trackingId(TrackingId val){
            trackingId = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder gender(Gender val) {
            gender = val;
            return this;
        }

        public Builder salary(Money val){
            salary = val;
            return this;
        }

        public Builder books(List<Book> val) {
            books = val;
            return this;
        }

        public Builder contact(Contact val) {
            contact = val;
            return this;
        }

        public Builder address(Address val) {
            address = val;
            return this;
        }

        public Builder isActive(Boolean val) {
            isActive = val;
            return this;
        }

        public Author build() {
            return new Author(this);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }
}
