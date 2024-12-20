package com.batu.grpc;

import com.batu.grpc.dto.author.create.CreateAuthorCommand;
import com.batu.grpc.dto.author.create.CreateAuthorResponse;
import com.batu.grpc.dto.author.track.TrackAuthorQuery;
import com.batu.grpc.dto.author.track.TrackAuthorResponse;
import com.batu.grpc.dto.book.create.CreateBookCommand;
import com.batu.grpc.dto.book.create.CreateBookResponse;
import com.batu.grpc.dto.book.track.TrackBookResponse;
import com.batu.grpc.dto.book.track.TrackBookStockQuery;
import com.batu.grpc.ports.input.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@RequiredArgsConstructor
@Service
@Validated
public class ApplicationServiceImpl implements ApplicationService {

    private final CreateAuthorCommandHandler createAuthorCommandHandler;
    private final TrackAuthorQueryHandler trackAuthorQueryHandler;
    private final CreateBookCommandHandler createBookCommandHandler;
    private final TrackBookQueryHandler trackBookQueryHandler;


    @Override
    public CreateAuthorResponse createAuthor(CreateAuthorCommand createAuthorCommand) {
        return createAuthorCommandHandler.createAuthor(createAuthorCommand);
    }

    @Override
    public TrackAuthorResponse trackAuthor(TrackAuthorQuery trackAuthorQuery) {
        return trackAuthorQueryHandler.trackAuthor(trackAuthorQuery);
    }

    @Override
    public CreateBookResponse createBook(CreateBookCommand createBookCommand) {
        return createBookCommandHandler.createBook(createBookCommand);
    }

    @Override
    public TrackBookResponse trackBook(TrackBookStockQuery trackBookStockQuery) {
        return trackBookQueryHandler.trackBook(trackBookStockQuery);
    }
}
