package com.batu.grpc.ports.output.repository;

import com.batu.grpc.domain.entity.Address;

public interface AddressRepository {
    Address save(Address address);
}
