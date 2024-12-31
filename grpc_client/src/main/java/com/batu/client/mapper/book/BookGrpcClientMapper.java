package com.batu.client.mapper.book;

import com.batu.BookStatus;
import com.batu.CreateBookCommand;
import com.batu.TrackBookQuery;
import com.batu.grpc.dto.book.create.CreateBookResponse;
import com.batu.grpc.dto.book.track.TrackBookResponse;
import com.batu.grpc.dto.book.track.TrackBookStockQuery;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BookGrpcClientMapper {

    public CreateBookCommand createBookCommandToGrpcCreateBookCommand(com.batu.grpc.dto.book.create.CreateBookCommand createBookCommand) {
        return CreateBookCommand.newBuilder()
                .setAuthorId(createBookCommand.getAuthorId().toString())
                .setPageSize(createBookCommand.getPageSize())
                .setPrice(createBookCommand.getPrice().toString())
                .setTitle(createBookCommand.getTitle())
                .setBookStatus(bookStatusToGrpcBookStatus(createBookCommand.getBookStatus()))
                .build();
    }

    public CreateBookResponse grpcCreateBookResponseToCreateBookResponse(com.batu.CreateBookResponse createBookResponse){
        return CreateBookResponse.builder()
                .bookId(UUID.fromString(createBookResponse.getBookId()))
                .authorId(UUID.fromString(createBookResponse.getAuthorId()))
                .skuCode(UUID.fromString(createBookResponse.getSkuCode()))
                .bookStatus(grpcBookStatusToBookStatus(createBookResponse.getBookStatus()))
                .message(createBookResponse.getMessage())
                .build();
    }

    public TrackBookQuery trackBookStockQueryToGrpcTrackBookQuery(TrackBookStockQuery trackBookStockQuery) {
        return TrackBookQuery.newBuilder()
                .setSkuCode(trackBookStockQuery.getSkuCode().getValue().toString())
                .build();
    }


    public TrackBookResponse grpcTrackBookResponseToTrackBookResponse(com.batu.TrackBookResponse trackBookResponse) {
        return TrackBookResponse.builder()
                .bookId(UUID.fromString(trackBookResponse.getBookId()))
                .authorId(UUID.fromString(trackBookResponse.getAuthorId()))
                .skuCode(UUID.fromString(trackBookResponse.getSkuCode()))
                .bookStatus(grpcBookStatusToBookStatus(trackBookResponse.getBookStatus()))
                .message(trackBookResponse.getMessage())
                .build();
    }


    private BookStatus bookStatusToGrpcBookStatus(com.batu.domain.valueobject.BookStatus bookStatus) {
        return BookStatus.valueOf(bookStatus.name());
    }

    private com.batu.domain.valueobject.BookStatus grpcBookStatusToBookStatus(BookStatus bookStatus){
        return com.batu.domain.valueobject.BookStatus.valueOf(bookStatus.name());
    }
}
