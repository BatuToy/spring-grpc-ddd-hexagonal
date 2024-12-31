package com.batu.client.rest.author;


import com.batu.client.GrpcClientService;
import com.batu.client.GrpcClientServiceImpl;
import com.batu.grpc.dto.author.create.CreateAuthorCommand;
import com.batu.grpc.dto.author.create.CreateAuthorResponse;
import com.batu.grpc.dto.author.track.TrackAuthorQuery;
import com.batu.grpc.dto.author.track.TrackAuthorResponse;
import com.batu.response.AppResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/author")
public class AuthorGrpcController {

    private final GrpcClientService grpcClientService;

    @PostMapping(value = "/create")
    public AppResponse<com.batu.grpc.dto.author.create.CreateAuthorResponse> createAuthor(@RequestBody CreateAuthorCommand createAuthorCommand){
        log.info("(Rest) \t Rest takes the request!");
        CreateAuthorResponse createAuthorResponse = grpcClientService.createAuthor(createAuthorCommand);
        log.info("(Rest) \t return response coming from the grpc server!");
        return new AppResponse<>(
                createAuthorResponse,
                HttpStatus.CREATED,
                "Author created by grpc protocol with author id= " + createAuthorResponse.getAuthorId()
        );
    }

    @PostMapping(value = "/track")
    public AppResponse<TrackAuthorResponse> trackAuthor(@RequestBody TrackAuthorQuery trackAuthorQuery){
        log.info("(Rest) \t Author starting to track with tracking id= {}", trackAuthorQuery.getTrackingId());
        TrackAuthorResponse trackAuthorResponse = grpcClientService.trackAuthor(trackAuthorQuery);
        log.info("(Rest) \t Author tracked successfully with author id= {}", trackAuthorResponse.getAuthorId());
        return new AppResponse<>(
                trackAuthorResponse,
                HttpStatus.OK,
                "Author successfully tracked with author id= " + trackAuthorResponse.getAuthorId()
        );

    }
}
