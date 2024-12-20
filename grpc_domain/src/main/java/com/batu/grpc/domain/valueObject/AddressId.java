package com.batu.grpc.domain.valueObject;

import com.batu.common.domain.valueobject.BaseId;

import java.util.UUID;

public class AddressId extends BaseId<UUID> {
    public AddressId(UUID value) {
        super(value);
    }
}
