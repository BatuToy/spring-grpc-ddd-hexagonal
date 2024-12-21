package com.batu.grpc.ports.output.repository;

import com.batu.grpc.domain.entity.Contact;

public interface ContactRepository {
    Contact save(Contact contact);
}
