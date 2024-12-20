package com.batu.grpc.dto.author.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Address {
    private final String city;
    private final String streetName;
    private final Integer postalCode;
}
