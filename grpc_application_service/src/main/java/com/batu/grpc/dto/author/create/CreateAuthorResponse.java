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

    private final UUID authorId;

    private final UUID authorTrackingId;

    private final UUID contactId;

    private final UUID addressId;

    private final String message;
}
