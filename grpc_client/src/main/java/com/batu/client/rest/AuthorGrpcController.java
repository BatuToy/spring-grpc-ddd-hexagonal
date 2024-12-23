package com.batu.grpc.rest;


import com.batu.grpc.GrpcClientService;
import com.batu.grpc.dto.author.create.CreateAuthorCommand;
import com.batu.grpc.dto.author.create.CreateAuthorResponse;
import com.batu.grpc.dto.author.track.TrackAuthorQuery;
import com.batu.grpc.dto.author.track.TrackAuthorResponse;
import com.batu.grpc.mapper.AuthorGrpcClientMapper;
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
@RequestMapping("/grpc/author")
public class AuthorGrpcController {
    private final GrpcClientService grpcClientService;
    private final AuthorGrpcClientMapper authorGrpcClientMapper;

    @PostMapping(value = "/create")
    public AppResponse<com.batu.grpc.dto.author.create.CreateAuthorResponse> createAuthor(@RequestBody CreateAuthorCommand createAuthorCommand){
        log.info("Rest takes the request!");
        com.batu.grpc.CreateAuthorCommand grpCreateAuthorCommand = authorGrpcClientMapper.createAuthorCommandToGrpcCreateAuthorCommand(createAuthorCommand);
        com.batu.grpc.CreateAuthorResponse grpcCreateAuthorResponse = grpcClientService.createAuthor(grpCreateAuthorCommand);
        com.batu.grpc.dto.author.create.CreateAuthorResponse createAuthorResponse = authorGrpcClientMapper.grpcCreateAuthorResponseToCreateAuthorResponse(grpcCreateAuthorResponse);
        log.info("Rest return response coming from the grpc server!");
        return new AppResponse<>(
                createAuthorResponse,
                HttpStatus.CREATED,
                "Author created by grpc protocol with author id= " + createAuthorResponse.getAuthorId()
        );
    }

    @PostMapping
    public AppResponse<TrackAuthorResponse> trackAuthor(@RequestBody TrackAuthorQuery trackAuthorQuery){
        log.info("(Rest) \t Author starting to track with tracking id= {}", trackAuthorQuery.getTrackingId());
        com.batu.grpc.TrackAuthorQuery grpcTrackAuthorQuery = authorGrpcClientMapper.trackAuthorQueryToGrpcTrackAuthorQuery(trackAuthorQuery);
        com.batu.grpc.TrackAuthorResponse grpcTrackAuthorResponse = grpcClientService.trackAuthor(grpcTrackAuthorQuery);
        TrackAuthorResponse trackAuthorResponse = authorGrpcClientMapper.grpcTrackAuthorResponseToTrackAuthorResponse(grpcTrackAuthorResponse);
        log.info("(Rest) \t Author tracked successfully with author id= {}", grpcTrackAuthorResponse.getAuthorId());
        return new AppResponse<>(
                trackAuthorResponse,
                HttpStatus.OK,
                "Author successfully tracked with author id= " + trackAuthorResponse.getAuthorId()
        );

    }
}
