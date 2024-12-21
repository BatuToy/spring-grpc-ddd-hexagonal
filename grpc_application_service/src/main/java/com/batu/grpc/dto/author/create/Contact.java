package com.batu.grpc.dto.author.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Contact {
    @NotNull
    private final String email;
    @NotNull
    private final String phoneNumber;
}
