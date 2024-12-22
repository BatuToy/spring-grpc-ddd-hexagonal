package com.batu.grpc.dto.book.create;

import com.batu.domain.valueobject.BookStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
public class CreateBookCommand {
    // This author id will be pulling from Auth service later! For now we give by hand!

    private final UUID authorId;

    private final String title;

    private final BookStatus bookStatus;

    private final BigDecimal price;

    private final Integer pageSize;
}
