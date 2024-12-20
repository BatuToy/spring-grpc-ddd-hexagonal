package com.batu.author.rest.book;

import com.batu.author.response.AppResponse;
import com.batu.grpc.dto.book.create.CreateBookCommand;
import com.batu.grpc.dto.book.create.CreateBookResponse;
import com.batu.grpc.ports.input.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/book")
public class BookController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<AppResponse<CreateBookResponse>> createBook(@RequestBody CreateBookCommand createBookCommand){
        log.info("Book is creating!");
        CreateBookResponse createBookResponse = applicationService.createBook(createBookCommand);
        log.info("Book created successfully with book id= {}", createBookResponse.getBookId());
        return ResponseEntity.ok( new AppResponse<>(
                createBookResponse,
                HttpStatus.CREATED,
                createBookResponse.getMessage()
        ));
    }

}
