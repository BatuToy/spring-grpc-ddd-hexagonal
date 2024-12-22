package com.batu.grpc.data.book.repo;

import com.batu.grpc.data.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookJpaRepository extends JpaRepository<BookEntity, UUID> {
    Optional<BookEntity> findBySkuCode(String skuCode);
}
