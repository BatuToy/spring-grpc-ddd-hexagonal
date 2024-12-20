package com.batu.grpc.dto.author.track;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackAuthorResponse {
    @NotNull
    private final UUID authorId;

    @NotNull
    private final UUID contactId;

    @NotNull
    private final UUID addressId;

    private final String message;
}
