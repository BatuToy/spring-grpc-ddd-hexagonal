package com.batu.grpc.dto.author.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateAuthorResponse {
    @NotNull
    private final UUID authorTrackingId;

    @NotNull
    private final UUID contactId;

    @NotNull
    private final UUID addressId;

    private final String message;
}
