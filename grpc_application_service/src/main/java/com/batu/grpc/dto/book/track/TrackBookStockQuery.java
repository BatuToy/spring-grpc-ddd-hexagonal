package com.batu.grpc.dto.book.track;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
public class TrackBookStockQuery {
    @NotNull
    private final SkuCode skuCode;
}
