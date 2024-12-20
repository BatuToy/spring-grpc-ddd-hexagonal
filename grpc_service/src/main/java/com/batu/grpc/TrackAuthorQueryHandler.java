package com.batu.grpc;

import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.exception.AuthorDomainException;
import com.batu.grpc.dto.author.track.TrackAuthorQuery;
import com.batu.grpc.dto.author.track.TrackAuthorResponse;
import com.batu.grpc.mapper.author.AuthorDataMapper;
import com.batu.grpc.ports.output.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrackAuthorQueryHandler {

    private final AuthorRepository authorRepository;
    private final AuthorDataMapper authorDataMapper;

    @Transactional(readOnly = true)
    public TrackAuthorResponse trackAuthor(TrackAuthorQuery trackAuthorQuery) {
        Optional<Author> author = authorRepository.findAuthorByTrackingId(trackAuthorQuery.getTrackingId());
        if(author.isEmpty()){
            log.error("Couldn't find author with tracking id= {}", trackAuthorQuery.getTrackingId());
            throw new AuthorDomainException("Author not found with tracking id= " + author.get().getTrackingId().getValue());
        }
        return authorDataMapper.authorToTrackAuthorResponse(author.get(),
                "Author with tracking id= "+ author.get().getTrackingId().getValue() +
                        " tracked successfully with author id= " + author.get().getTrackingId().getValue());
    }
}
