package com.batu.grpc.dto.book.create;

import com.batu.domain.valueobject.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
@Builder
public class CreateBookResponse {
    private final UUID bookId;
    private final UUID skuCode;
    private final UUID authorId;
    private final BookStatus bookStatus;
    private final String message;
}
