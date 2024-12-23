package com.batu.grpc;

import com.batu.grpc.CreateAuthorCommand;
import com.batu.grpc.CreateAuthorResponse;
import com.batu.grpc.TrackAuthorQuery;
import com.batu.grpc.TrackAuthorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class GrpcClientService {

    @GrpcClient("grpc-client-service")
    private final ApplicationServiceGrpcServerGrpc.ApplicationServiceGrpcServerBlockingStub blockingStub;


    public CreateAuthorResponse createAuthor(CreateAuthorCommand createAuthorCommand){
        log.info("Grpc client making request!");
        CreateAuthorResponse response = blockingStub.createAuthor(createAuthorCommand);
        log.info("Grpc protocol return response with= {}", response);
        return response;
    }

    public TrackAuthorResponse trackAuthor(TrackAuthorQuery trackAuthorQuery){
        log.info("(gRPC) \t Author starting to tracking with tracking id= {}", trackAuthorQuery.getTrackingId());
        com.batu.grpc.TrackAuthorResponse response = blockingStub.trackAuthor(trackAuthorQuery);
        log.info("(gRPC) \t Author tracked successfully with author id= {}", response.getAuthorId());
        return response;
    }
}
