package com.batu.grpc.domain.valueObject;

import com.batu.domain.valueobject.BaseId;

import java.util.UUID;

public class AuthorId extends BaseId<UUID> {
    public AuthorId(UUID value) {
        super(value);
    }
}
