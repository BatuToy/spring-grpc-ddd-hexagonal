package com.batu.grpc.dto.author.create;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
public class SkuCode {
    @NotNull
    private final UUID skuCode;
}
