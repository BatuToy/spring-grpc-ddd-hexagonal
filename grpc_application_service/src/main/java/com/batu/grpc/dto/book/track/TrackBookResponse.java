package com.batu.grpc.dto.book.track;

import com.batu.domain.valueobject.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class TrackBookResponse {
    private final UUID bookId;
    private final UUID AuthorId;
    private final UUID skuCode;
    private final BookStatus bookStatus;
    private final String message;
}
