package com.batu.grpc.data.author.repo;

import com.batu.grpc.data.author.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuthorJpaRepository extends JpaRepository<AuthorEntity, UUID> {
    Optional<AuthorEntity> findByTrackingId(UUID trackingId);
}
