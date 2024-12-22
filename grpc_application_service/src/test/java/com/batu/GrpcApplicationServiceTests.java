package com.batu;

import com.batu.domain.valueobject.BookStatus;
import com.batu.domain.valueobject.Gender;
import com.batu.domain.valueobject.Money;
import com.batu.grpc.domain.entity.Author;
import com.batu.grpc.domain.valueObject.AuthorId;
import com.batu.grpc.dto.author.create.*;
import com.batu.grpc.dto.book.create.Book;
import com.batu.grpc.dto.author.track.TrackAuthorQuery;
import com.batu.grpc.dto.author.track.TrackAuthorResponse;
import com.batu.grpc.mapper.author.AuthorDataMapper;
import com.batu.grpc.ports.input.service.ApplicationService;
import com.batu.grpc.ports.output.repository.AuthorRepository;
import com.batu.grpc.ports.output.repository.BookRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = TestConfigurations.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GrpcApplicationServiceTests {

    @Autowired
    private  ApplicationService applicationService;
    @Autowired
    private AuthorDataMapper authorDataMapper;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;


    private CreateAuthorCommand createAuthorCommand;
    private TrackAuthorQuery trackAuthorQuery;
    private final UUID AUTHOR_ID = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
    private final UUID TRACKING_ID = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
    private final UUID SKU_CODE = UUID.randomUUID();
    private final UUID BOOK_ID_2 = UUID.fromString("f6ce7b3d-dbe2-4720-8f7b-37e3980d75c9");
    private final UUID BOOK_ID_1 = UUID.fromString("f8c3de3d-1fea-4d7c-a8b0-29f63c4c3454");
    private final UUID CONTACT_ID = UUID.randomUUID();
    private final UUID ADDRESS_ID = UUID.randomUUID();
    private final Integer PAGE_SIZE = 150;
    private final BigDecimal BOOK_1_PRICE = new BigDecimal("350.00");
    private final BigDecimal BOOK_2_PRICE = new BigDecimal("150.00");
    private final BigDecimal BOOK_3_PRICE = new BigDecimal("450.00");

    @BeforeAll
    public void init(){
        createAuthorCommand = CreateAuthorCommand.builder()
                .firstName("Batuhan")
                .lastName("Toy")
                .gender(Gender.MALE)
                .salary(new BigDecimal("100000.00"))
                .contact( new Contact(
                        "batu_toy@hotmail.com",
                        "0505 663 40 81"
                ))
                .address( new Address(
                        "Izmir",
                        "Saman-yol",
                        35000
                ))
                .books(
                        List.of(
                                Book.builder()
                                        .bookStatus( BookStatus.IN_STOCK)
                                        .price( new Money(BOOK_1_PRICE))
                                        .pageSize(PAGE_SIZE)
                                        .title("Cars")
                                        .build(),
                                Book.builder()
                                        .bookStatus( BookStatus.IN_STOCK)
                                        .price( new Money(BOOK_2_PRICE))
                                        .pageSize(PAGE_SIZE)
                                        .title("Books")
                                        .build()
                        )).build();



        trackAuthorQuery = TrackAuthorQuery.builder()
                .trackingId(TRACKING_ID)
                .build();

        Author author = authorDataMapper.createAuthorCommandToAuthor(createAuthorCommand);
        author.setId(new AuthorId(AUTHOR_ID));

        when(authorRepository.save(any(Author.class))).thenReturn(author);
        when(authorRepository.findAuthorByTrackingId(TRACKING_ID)).thenReturn(Optional.of(author));
    }

    @Test
    void createAuthor(){
        CreateAuthorResponse response = applicationService.createAuthor(createAuthorCommand);
        assertEquals(TRACKING_ID, response.getAuthorTrackingId());
        assertEquals(CONTACT_ID, response.getContactId());
        assertEquals(ADDRESS_ID, response.getAddressId());
        assertEquals("Author with id= "+ AUTHOR_ID +" created successfully!", response.getMessage());
    }

    @Test
    void trackAuthorByTrackingId(){
        // Need to prepare the correct Author for tracking in beforeAll.
        TrackAuthorResponse response = applicationService.trackAuthor(trackAuthorQuery);
        assertEquals(TRACKING_ID, response.getAuthorId());
    }
}
