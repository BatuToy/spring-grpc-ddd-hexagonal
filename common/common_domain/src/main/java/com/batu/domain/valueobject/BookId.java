package com.batu.domain.valueobject;

import java.util.UUID;

public class BookId extends BaseId<UUID> {
    public BookId(UUID value) {
        super(value);
    }
}
