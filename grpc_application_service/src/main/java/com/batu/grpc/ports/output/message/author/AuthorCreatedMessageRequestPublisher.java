package com.batu.grpc.ports.output.message.author;

import com.batu.domain.event.publisher.DomainEventPublisher;
import com.batu.grpc.domain.event.AuthorCreatedEvent;

public interface AuthorCreatedMessageRequestPublisher extends DomainEventPublisher<AuthorCreatedEvent> {
    @Override
    void publish(AuthorCreatedEvent domainEvent);
}
