package com.batu.grpc.data.author.adapter;

import com.batu.grpc.data.author.mapper.AuthorDataAccessMapper;
import com.batu.grpc.data.author.repo.AuthorJpaRepository;
import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.ports.output.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Component
public class AuthorRepositoryImpl implements AuthorRepository {

    private final AuthorDataAccessMapper authorDataAccessMapper;
    private final AuthorJpaRepository authorJpaRepository;

    @Override
    public Author save(Author author) {
        return authorDataAccessMapper
                .authorEntityToAuthor(
                        authorJpaRepository
                                .save(authorDataAccessMapper.authorToAuthorEntity(author)));
    }

    @Override
    public Optional<Author> findAuthorByTrackingId(UUID trackingId) {
        return authorJpaRepository.findByTrackingId(trackingId)
                .map(authorDataAccessMapper::authorEntityToAuthor);
    }

    @Override
    public Optional<Author> findAuthorByAuthorId(UUID authorId) {
        return authorJpaRepository.findById(authorId)
                .map(authorDataAccessMapper::authorEntityToAuthor);
    }
}
