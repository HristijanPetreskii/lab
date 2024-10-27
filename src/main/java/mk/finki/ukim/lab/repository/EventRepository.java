package mk.finki.ukim.lab.repository;

import mk.finki.ukim.lab.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EventRepository {
    private final List<Event> events;

    public EventRepository() {
        events = new ArrayList<>();
        // Initialize the list with 10 sample Event instances
        events.add(new Event("Music Concert", "An amazing music event", 8.5));
        events.add(new Event("Art Exhibition", "Exhibit of modern art pieces", 7.2));
        events.add(new Event("Science Fair", "A fair showcasing science projects", 9.0));
        events.add(new Event("Book Fair", "Meet authors and discover new books", 6.8));
        events.add(new Event("Film Festival", "Screening of award-winning films", 8.0));
        events.add(new Event("Food Festival", "Taste dishes from around the world", 7.5));
        events.add(new Event("Tech Conference", "Latest trends in technology", 9.2));
        events.add(new Event("Theater Play", "A classic play performance", 7.1));
        events.add(new Event("Marathon", "Annual city marathon", 8.3));
        events.add(new Event("Charity Gala", "Fundraiser for local charities", 6.9));
    }

    // Method to return all events
    public List<Event> findAll() {
        return events;
    }

    // Method to search events by text in name or description
    public List<Event> searchEvents(String text) {
        return events.stream()
                .filter(event -> event.getName().toLowerCase().contains(text.toLowerCase())
                        || event.getDescription().toLowerCase().contains(text.toLowerCase()))
                .collect(Collectors.toList());
    }
}