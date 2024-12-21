package com.batu.grpc.dto.book.create;

import com.batu.common.domain.valueobject.BookStatus;
import com.batu.common.domain.valueobject.Money;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
