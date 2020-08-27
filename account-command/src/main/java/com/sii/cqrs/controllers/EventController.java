package com.sii.cqrs.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/events")
public class EventController {

    private EventStore eventStore;

    @GetMapping
    @RequestMapping("/{aggregateId}")
    @Transactional(readOnly = true)
    public List<Object> listEvents(@PathVariable String aggregateId) {
        // eventStore.readEvents(aggregateId).asStream().collect(Collectors.toList());
        return eventStore.readEvents(aggregateId).asStream().map(event -> {
            System.out.println(event.getAggregateIdentifier());
            var result = new HashMap<String, Object>();
            result.put("aggregateIdentifier", event.getAggregateIdentifier());
            result.put("payload", event.getPayload());
            result.put("identifier", event.getIdentifier());
            result.put("type", event.getType());
            result.put("payloadType", event.getPayloadType());
            result.put("sequenceNumber", event.getSequenceNumber());
            result.put("timestamp", event.getTimestamp());
            return result;
        }).collect(Collectors.toList());

    }

}
