package com.batu.grpc;

import com.batu.grpc.dto.book.track.TrackBookResponse;
import com.batu.grpc.dto.book.track.TrackBookStockQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@RequiredArgsConstructor
@Slf4j
public class TrackBookQueryHandler {
    public TrackBookResponse trackBook(TrackBookStockQuery trackBookStockQuery) {
        return null;
    }
}
