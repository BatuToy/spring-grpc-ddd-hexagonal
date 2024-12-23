package com.batu.grpc.dto.author.create;

import com.batu.domain.valueobject.Gender;
import com.batu.grpc.dto.book.create.Book;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CreateAuthorCommand {
    @NotNull
    private final String firstName;
    @NotNull
    private final String lastName;
    @NotNull
    private final Gender gender;
    @NotNull
    private final BigDecimal salary;

    private final List<Book> books;

    @NotNull
    private final Address address;
    @NotNull
    private final Contact contact;
}
