package mk.finki.ukim.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.lab.model.EventBooking;
import mk.finki.ukim.lab.service.EventBookingService;

/*import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/
import java.io.IOException;

@WebServlet(name = "EventBookingServlet", urlPatterns = "/eventBooking")
public class EventBookingServlet extends HttpServlet {
    private final EventBookingService eventBookingService;

    public EventBookingServlet(EventBookingService eventBookingService) {
        this.eventBookingService = eventBookingService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String eventName = req.getParameter("selectedEvent");
        String attendeeName = req.getParameter("attendeeName");
        String attendeeAddress = req.getRemoteAddr();
        int numberOfTickets = Integer.parseInt(req.getParameter("numTickets"));

        // Create a booking
        EventBooking booking = eventBookingService.placeBooking(eventName, attendeeName, attendeeAddress, numberOfTickets);

        // Set attributes for the confirmation page
        req.setAttribute("booking", booking);
        req.setAttribute("clientIp", attendeeAddress);

        // Forward to the confirmation page
        req.getRequestDispatcher("/WEB-INF/templates/bookingConfirmation.html").forward(req, resp);
    }
}
