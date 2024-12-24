package com.batu.client.rest.book;

import com.batu.client.service.GrpcClientService;
import com.batu.grpc.dto.book.create.CreateBookCommand;
import com.batu.grpc.dto.book.create.CreateBookResponse;
import com.batu.grpc.dto.book.track.TrackBookResponse;
import com.batu.grpc.dto.book.track.TrackBookStockQuery;
import com.batu.response.AppResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/book")
public class BookGrpcController {

    private final GrpcClientService grpcClientService;

    @PostMapping(value = "/create")
    public AppResponse<CreateBookResponse> createBook(@RequestBody CreateBookCommand createBookCommand){
        log.info("(Rest) \t Book starting to creating!");
        CreateBookResponse createBookResponse = grpcClientService.createBook(createBookCommand);
        log.info("(Rest) \t Book was created successfully with book id= {}", createBookResponse.getBookId());
        return new AppResponse<>(
                createBookResponse,
                HttpStatus.CREATED,
                "Book created successfully with author id= " + createBookResponse.getAuthorId()
        );
    }

    public AppResponse<TrackBookResponse> trackBook(TrackBookStockQuery trackBookStockQuery){
        log.info("(Rest) \t Book starting to tracking with sku code= {}", trackBookStockQuery.getSkuCode().getValue());
        TrackBookResponse trackBookResponse = grpcClientService.trackBook(trackBookStockQuery);
        log.info("(Rest) \t Book was tracked successfully for book id= {}", trackBookResponse.getBookId());
        return new AppResponse<>(
                trackBookResponse,
                HttpStatus.ACCEPTED,
                "Book tracked successfully!"
        );
    }
}
