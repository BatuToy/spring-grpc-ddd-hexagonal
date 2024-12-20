package com.batu.grpc.dto.author.create;

import com.batu.common.domain.valueobject.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CreateAuthorCommand {
    private final String firstName;
    private final String lastName;
    private final Gender gender;
    private final BigDecimal salary;
    private final List<Book> books;
    private final Address address;
    private final Contact contact;
}
