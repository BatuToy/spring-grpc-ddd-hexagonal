package com.batu.grpc.dto.author.track;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TrackAuthorQuery {
    @NotNull
    private  UUID trackingId;
}
