package com.batu.grpc.domain.entity;

import com.batu.common.domain.entity.BaseEntity;
import com.batu.grpc.domain.valueObject.AuthorId;
import com.batu.grpc.domain.valueObject.ContactId;

import java.util.UUID;

public class Contact extends BaseEntity<ContactId> {

    private final String email;
    private final String phoneNumber;
    private AuthorId authorId;

    public Contact(ContactId contactId, String email, String phoneNumber) {
        super.setId(contactId);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Contact(String email, String phoneNumber){
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void initializeContactToAuthor(AuthorId authorId, ContactId contactId){
        this.authorId = authorId;
        super.setId(contactId);
    }

    public void initializeWithoutContactIdToAuthor(AuthorId authorId){
        super.setId(new ContactId(UUID.randomUUID()));
        this.authorId = authorId;
    }

    public AuthorId getAuthorId() {
        return authorId;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
