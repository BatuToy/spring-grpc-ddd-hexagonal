package com.batu.grpc.data.author.adapter;

import com.batu.grpc.data.author.mapper.AuthorDataAccessMapper;
import com.batu.grpc.data.author.repo.AddressJpaRepository;
import com.batu.grpc.domain.entity.Address;
import com.batu.grpc.ports.output.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AddressRepositoryImpl implements AddressRepository {

    private final AddressJpaRepository addressJpaRepository;
    private final AuthorDataAccessMapper authorDataAccessMapper;

    @Override
    public Address save(Address address) {
        return authorDataAccessMapper
                .addressEntityToAddress
                        (addressJpaRepository
                                .save(authorDataAccessMapper.addressToAddressEntity(address)));
    }
}
