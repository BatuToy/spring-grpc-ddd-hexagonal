package com.batu.grpc.domain.entity;

import com.batu.common.domain.entity.BaseEntity;
import com.batu.grpc.domain.valueObject.AddressId;
import com.batu.grpc.domain.valueObject.AuthorId;

import java.util.UUID;

public class Address extends BaseEntity<AddressId> {
    private final String city;
    private final String streetName;
    private final Integer postalCode;

    private AuthorId authorId;

    public Address(AddressId addressId, String city, String streetName, Integer postalCode) {
        super.setId(addressId);
        this.city = city;
        this.streetName = streetName;
        this.postalCode = postalCode;
    }

    public Address(String city, String streetName, Integer postalCode) {
        this.city = city;
        this.streetName = streetName;
        this.postalCode = postalCode;
    }

    public void initializeAddressToAuthor(AuthorId  authorId, AddressId addressId){
        this.authorId = authorId;
        super.setId(addressId);
    }

    public void initializeWithoutAddressIdToAuthor(AuthorId authorId){
        super.setId(new AddressId(UUID.randomUUID()));
        this.authorId = authorId;
    }

    public String getCity() {
        return city;
    }

    public String getStreetName() {
        return streetName;
    }

    public Integer getPostalCode() {
        return postalCode;
    }
}
