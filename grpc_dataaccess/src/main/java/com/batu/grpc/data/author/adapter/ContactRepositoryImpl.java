package com.batu.grpc.data.author.adapter;

import com.batu.grpc.data.author.mapper.AuthorDataAccessMapper;
import com.batu.grpc.data.author.repo.AuthorJpaRepository;
import com.batu.grpc.data.author.repo.ContactJpaRepository;
import com.batu.grpc.domain.entity.Contact;
import com.batu.grpc.ports.output.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ContactRepositoryImpl implements ContactRepository {

    private final ContactJpaRepository contactJpaRepository;
    private final AuthorDataAccessMapper authorDataAccessMapper;

    @Override
    public Contact save(Contact contact) {
        return authorDataAccessMapper
                .contactEntityToContact
                        (contactJpaRepository
                                .save(authorDataAccessMapper.contactToContactEntity(contact)));
    }
}
