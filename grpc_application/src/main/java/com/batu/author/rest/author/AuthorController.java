package com.batu.author.rest.author;

import com.batu.author.response.AppResponse;
import com.batu.grpc.dto.author.create.CreateAuthorCommand;
import com.batu.grpc.dto.author.create.CreateAuthorResponse;
import com.batu.grpc.dto.author.track.TrackAuthorQuery;
import com.batu.grpc.dto.author.track.TrackAuthorResponse;
import com.batu.grpc.ports.input.service.ApplicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(value = "/author")
@RequiredArgsConstructor
public class AuthorController {

    private final ApplicationService applicationService;

    @PostMapping(value = "/create")
    public AppResponse<CreateAuthorResponse> createAuthor(@RequestBody CreateAuthorCommand createAuthorCommand){
        log.info("Creating author!");
        CreateAuthorResponse createAuthorResponse = applicationService.createAuthor(createAuthorCommand);
        log.info("Author was created with author Tracking id= {}", createAuthorResponse.getAuthorTrackingId());
        return new AppResponse<>(
                createAuthorResponse,
                HttpStatus.CREATED,
                "Author created successfully with author id= " + createAuthorResponse.getAuthorId()
        );
    }

    @GetMapping(value = "/{trackingId}")
    public ResponseEntity<AppResponse<TrackAuthorResponse>> trackAuthor(@PathVariable UUID trackingId){
        log.info("Tracking author with tracking id= {}", trackingId);
        TrackAuthorResponse trackAuthorResponse = applicationService.trackAuthor(TrackAuthorQuery.builder().trackingId(trackingId).build());
        log.info("Author was tracked successfully with authorId= {}", trackAuthorResponse.getAuthorId());
        return ResponseEntity.ok(new AppResponse<>(
                trackAuthorResponse,
                HttpStatus.ACCEPTED,
                trackAuthorResponse.getMessage()
        ));
    }
}
