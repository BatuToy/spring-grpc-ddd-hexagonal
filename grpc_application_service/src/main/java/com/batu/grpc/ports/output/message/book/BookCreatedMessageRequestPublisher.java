package com.batu.grpc.ports.output.message.book;

import com.batu.domain.event.publisher.DomainEventPublisher;
import com.batu.grpc.domain.event.BookCreatedEvent;

public interface BookCreatedMessageRequestPublisher extends DomainEventPublisher<BookCreatedEvent> {
    @Override
    void publish(BookCreatedEvent domainEvent);
}
