package com.batu.grpc.domain.valueObject;

import com.batu.common.domain.valueobject.BaseId;

import java.util.UUID;

public class SkuCode extends BaseId<UUID> {

    public SkuCode(UUID value) {
        super(value);
    }
}
