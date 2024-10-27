package mk.finki.ukim.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.lab.model.Event;
import mk.finki.ukim.lab.service.EventService;

/*import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/
import java.io.IOException;
import java.util.List;

@WebServlet(name = "EventListServlet", urlPatterns = "/")
public class EventListServlet extends HttpServlet {
    private final EventService eventService;

    public EventListServlet(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchText = req.getParameter("searchText");
        String minRatingParam = req.getParameter("minRating");

        List<Event> events;
        if (searchText != null || minRatingParam != null) {
            double minRating = minRatingParam != null && !minRatingParam.isEmpty()
                    ? Double.parseDouble(minRatingParam)
                    : 0.0;
            events = eventService.searchEvents(searchText, minRating);
        } else {
            events = eventService.listAll();
        }

        req.setAttribute("events", events);
        req.getRequestDispatcher("/WEB-INF/templates/listEvents.html").forward(req, resp);
    }
}