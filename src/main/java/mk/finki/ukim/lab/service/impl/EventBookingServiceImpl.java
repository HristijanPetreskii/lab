package mk.finki.ukim.lab.service.impl;

import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.repository.EventRepository;
import mk.finki.ukim.lab.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> listAll() {
        return eventRepository.findAll();
    }

    @Override
    public java.util.List<Event> searchEvents(String text, double minRating) {
        return eventRepository.searchEvents(text);
    }
}
