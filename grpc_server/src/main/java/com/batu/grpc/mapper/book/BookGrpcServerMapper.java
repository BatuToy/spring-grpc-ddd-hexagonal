package com.batu.grpc.mapper.book;

import com.batu.domain.valueobject.BookStatus;
import com.batu.TrackBookQuery;
import com.batu.TrackBookResponse;
import com.batu.grpc.dto.book.create.CreateBookCommand;
import com.batu.grpc.dto.book.create.CreateBookResponse;
import com.batu.grpc.dto.book.track.SkuCode;
import com.batu.grpc.dto.book.track.TrackBookStockQuery;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class BookGrpcServerMapper {

    public CreateBookCommand grpcCreateBookCommandToCreateBookCommand(com.batu.CreateBookCommand createBookCommand) {
        return CreateBookCommand.builder()
                .authorId(UUID.fromString(createBookCommand.getAuthorId()))
                .title(createBookCommand.getTitle())
                .price(new BigDecimal(createBookCommand.getPrice()))
                .pageSize(createBookCommand.getPageSize())
                .bookStatus(grpcBookStatusToBookStatus(createBookCommand.getBookStatus()))
                .build();
    }

    public com.batu.CreateBookResponse createBookResponseToGrpcCreateBookResponse(CreateBookResponse createBookResponse) {
        return com.batu.CreateBookResponse.newBuilder()
                .setBookId(createBookResponse.getBookId().toString())
                .setAuthorId(createBookResponse.getAuthorId().toString())
                .setSkuCode(createBookResponse.getSkuCode().toString())
                .setBookStatus(bookStatusToGrpcBookStatus(createBookResponse.getBookStatus()))
                .setMessage(createBookResponse.getMessage())
                .build();
    }

    public TrackBookStockQuery grpcTrackBookQueryToTrackBookStockQuery(TrackBookQuery trackBookQuery){
        return TrackBookStockQuery.builder()
                .skuCode(new SkuCode(UUID.fromString(trackBookQuery.getSkuCode())))
                .build();
    }

    public TrackBookResponse trackBookResponseToGrpcTrackBookResponse(com.batu.grpc.dto.book.track.TrackBookResponse trackBookResponse){
        return TrackBookResponse.newBuilder()
                .setBookId(trackBookResponse.getBookId().toString())
                .setAuthorId(trackBookResponse.getAuthorId().toString())
                .setSkuCode(trackBookResponse.getSkuCode().toString())
                .setBookStatus(bookStatusToGrpcBookStatus(trackBookResponse.getBookStatus()))
                .setMessage(trackBookResponse.getMessage())
                .build();
    }

    private com.batu.BookStatus bookStatusToGrpcBookStatus(BookStatus bookStatus) {
        return com.batu.BookStatus.valueOf(bookStatus.name());
    }

    private BookStatus grpcBookStatusToBookStatus(com.batu.BookStatus bookStatus) {
        return BookStatus.valueOf(bookStatus.name());
    }

}
