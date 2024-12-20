package com.batu.grpc.domain;

import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.event.AuthorCreatedEvent;
import com.batu.grpc.domain.valueObject.AuthorId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static com.batu.common.domain.DomainConstants.UTC;

@Slf4j
@RequiredArgsConstructor
public class AuthorDomainServiceImpl implements AuthorDomainService {

    @Override
    public AuthorCreatedEvent validateAndInitializeAuthor(Author author) {
        log.info("Author starting to initialize!");
        author.validateAuthor();
        author.initializeAuthor();
        log.info("Books are initialized to author with id= {}", author.getId().getValue());
        return new AuthorCreatedEvent(author, ZonedDateTime.now(ZoneId.of(UTC)));
    }

}
