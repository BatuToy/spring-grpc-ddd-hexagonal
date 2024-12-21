package com.batu.grpc.dto.book.track;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
public class TrackBookStockQuery {
    private final SkuCode skuCode;
}
