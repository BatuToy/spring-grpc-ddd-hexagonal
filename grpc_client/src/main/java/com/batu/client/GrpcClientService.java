package com.batu.client;

import com.batu.grpc.dto.author.create.CreateAuthorCommand;
import com.batu.grpc.dto.author.create.CreateAuthorResponse;
import com.batu.grpc.dto.author.track.TrackAuthorQuery;
import com.batu.grpc.dto.author.track.TrackAuthorResponse;
import com.batu.grpc.dto.book.create.CreateBookCommand;
import com.batu.grpc.dto.book.create.CreateBookResponse;
import com.batu.grpc.dto.book.track.TrackBookResponse;
import com.batu.grpc.dto.book.track.TrackBookStockQuery;
import jakarta.validation.Valid;

public interface GrpcClientService {
    CreateAuthorResponse createAuthor(@Valid CreateAuthorCommand createAuthorCommand);
    TrackAuthorResponse trackAuthor(@Valid  TrackAuthorQuery trackAuthorQuery);
    CreateBookResponse createBook(@Valid CreateBookCommand createBookCommand);
    TrackBookResponse trackBook(@Valid TrackBookStockQuery trackBookStockQuery);
}
