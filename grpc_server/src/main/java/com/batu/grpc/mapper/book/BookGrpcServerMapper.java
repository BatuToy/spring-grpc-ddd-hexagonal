package com.batu.grpc.mapper.book;

import com.batu.domain.valueobject.BookStatus;
import com.batu.grpc.Book;
import com.batu.grpc.TrackBookQuery;
import com.batu.grpc.TrackBookResponse;
import com.batu.grpc.dto.book.create.CreateBookCommand;
import com.batu.grpc.dto.book.create.CreateBookResponse;
import com.batu.grpc.dto.book.track.SkuCode;
import com.batu.grpc.dto.book.track.TrackBookStockQuery;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class BookGrpcServerMapper {

    public CreateBookCommand grpcCreateBookCommandToCreateBookCommand(com.batu.grpc.CreateBookCommand createBookCommand) {
        return CreateBookCommand.builder()
                .authorId(UUID.fromString(createBookCommand.getAuthorId()))
                .title(createBookCommand.getTitle())
                .price(new BigDecimal(createBookCommand.getPrice()))
                .pageSize(createBookCommand.getPageSize())
                .bookStatus(grpcBookStatusToBookStatus(createBookCommand.getBookStatus()))
                .build();
    }

    public com.batu.grpc.CreateBookResponse createBookResponseToGrpcCreateBookResponse(CreateBookResponse createBookResponse) {
        return com.batu.grpc.CreateBookResponse.newBuilder()
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

    private com.batu.grpc.BookStatus bookStatusToGrpcBookStatus(BookStatus bookStatus) {
        return com.batu.grpc.BookStatus.valueOf(bookStatus.name());
    }

    private BookStatus grpcBookStatusToBookStatus(com.batu.grpc.BookStatus bookStatus) {
        return BookStatus.valueOf(bookStatus.name());
    }

}
