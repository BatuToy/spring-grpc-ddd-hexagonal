package com.batu.grpc.data.author.repo;

import com.batu.grpc.data.author.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContactJpaRepository extends JpaRepository<ContactEntity, UUID> {}
