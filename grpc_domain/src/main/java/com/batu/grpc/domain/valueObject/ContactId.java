package com.batu.grpc.domain.valueObject;

import com.batu.domain.valueobject.BaseId;

import java.util.UUID;

public class ContactId extends BaseId<UUID> {
    public ContactId(UUID value) {
        super(value);
    }
}
