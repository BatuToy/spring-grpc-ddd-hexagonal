package com.batu.common.domain.event.publisher;

import com.batu.common.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T domainEvent);
}
