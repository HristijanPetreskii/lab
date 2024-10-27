package mk.finki.ukim.lab.service.impl;

import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.model.EventBooking;
import mk.finki.ukim.lab.repository.EventRepository;
import mk.finki.ukim.lab.service.EventBookingService;
import mk.finki.ukim.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

class EventBookingServiceImpl implements EventBookingService {
    private final EventRepository eventRepository;

    public EventBookingServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventBooking placeBooking(String eventName, String attendeeName, String attendeeAddress, int numberOfTickets) {
        // Find event by name
        Event event = eventRepository.findAll().stream()
                .filter(e -> e.getName().equalsIgnoreCase(eventName))
                .findFirst()
                .orElse(null);

        if (event == null) {
            throw new IllegalArgumentException("Event not found: " + eventName);
        }

        // Create and return new EventBooking
        return new EventBooking(eventName, attendeeName, attendeeAddress, (long) numberOfTickets);
    }
}