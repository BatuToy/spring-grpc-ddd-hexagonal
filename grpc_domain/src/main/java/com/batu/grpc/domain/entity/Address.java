package com.batu.grpc.domain.entity;

import com.batu.domain.entity.BaseEntity;
import com.batu.grpc.domain.valueObject.AddressId;
import com.batu.grpc.domain.valueObject.AuthorId;

import java.util.UUID;

public class Address extends BaseEntity<AddressId> {
    private final String country;
    private final String city;
    private final String streetName;
    private final Integer postalCode;

    private AuthorId authorId;

    public Address(AddressId addressId, String country, String city, String streetName, Integer postalCode) {
        this.country = country;
        super.setId(addressId);
        this.city = city;
        this.streetName = streetName;
        this.postalCode = postalCode;
    }

    public Address(String country, String city, String streetName, Integer postalCode) {
        this.country = country;
        this.city = city;
        this.streetName = streetName;
        this.postalCode = postalCode;
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

    public String getCountry() {
        return country;
    }
}
