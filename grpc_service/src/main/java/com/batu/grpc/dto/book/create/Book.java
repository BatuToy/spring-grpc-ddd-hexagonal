package com.batu.grpc.dto.author.create;

import com.batu.common.domain.valueobject.BookStatus;
import com.batu.common.domain.valueobject.Money;
import com.batu.grpc.domain.valueObject.AuthorId;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Book {
    @NotNull
    private final BookStatus bookStatus;
    @NotNull
    private final Integer pageSize;
    @NotNull
    private final String title;
    @NotNull
    private Money price;

}
