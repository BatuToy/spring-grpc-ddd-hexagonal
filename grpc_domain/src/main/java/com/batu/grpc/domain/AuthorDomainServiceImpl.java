package com.batu.grpc.domain;

import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.event.AuthorCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.batu.domain.DomainConstants.UTC;

@Slf4j
@RequiredArgsConstructor
public class AuthorDomainServiceImpl implements AuthorDomainService {

    @Override
    public AuthorCreatedEvent validateAndInitializeAuthor(Author author) {
        log.info("Author starting to initialize!");
        author.validateAuthor();
        author.initializeAuthor();
        log.info("Author was initialized with author id= {}", author.getId().getValue());
        return new AuthorCreatedEvent(author, ZonedDateTime.now(ZoneId.of(UTC)));
    }

}
