package com.batu.grpc;

import com.batu.grpc.domain.event.AuthorCreatedEvent;
import com.batu.grpc.dto.author.create.CreateAuthorCommand;
import com.batu.grpc.dto.author.create.CreateAuthorResponse;
import com.batu.grpc.mapper.author.AuthorDataMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAuthorCommandHandler {

    private final CreateAuthorCommandHelper createAuthorCommandHelper;
    private final AuthorDataMapper authorDataMapper;
    //private final AuthorCreatedMessageRequestPublisher authorCreatedMessageRequestPublisher;

    public CreateAuthorResponse createAuthor(CreateAuthorCommand createAuthorCommand) {
        AuthorCreatedEvent authorCreatedEvent = createAuthorCommandHelper.persisAuthor(createAuthorCommand);
        //authorCreatedMessageRequestPublisher.publish(authorCreatedEvent);
        return authorDataMapper.authorToCreateAuthorResponse(authorCreatedEvent.getAuthor(),
                "Author with id= "+ authorCreatedEvent.getAuthor().getId().getValue() +" created successfully!");
    }


}
