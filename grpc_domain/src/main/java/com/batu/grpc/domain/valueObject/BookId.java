package com.batu.grpc.domain.valueObject;

import com.batu.common.domain.valueobject.BaseId;

import java.util.UUID;

public class BookId extends BaseId<UUID> {
    public BookId(UUID value) {
        super(value);
    }
}
