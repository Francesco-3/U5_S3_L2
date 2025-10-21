package francescodicecca.SpringJWT.controllers;

import francescodicecca.TravelManagement.entities.Booking;
import francescodicecca.TravelManagement.payloads.NewBookingPayload;
import francescodicecca.TravelManagement.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    //GET ALL <- lista delle prenotazioni
    @GetMapping
    public Page<Booking> getAllBookings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "requestDate") String sort
    ) { return this.bookingService.getBooking(page, size, sort); }

    //GET ONE BY ID <- singola prenotazione
    @GetMapping("/{bookingId}")
    public Booking getBookingById(@PathVariable UUID bookingId) {
        return this.bookingService.findBookingById(bookingId);
    }

    //POST BY ID <- crea una nuova prenotazione
    @PostMapping("/employee/{employeeId}/trip/{tripId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@PathVariable UUID employeeId, @PathVariable UUID tripId, @RequestBody @Validated NewBookingPayload payload)
    { return this.bookingService.saveBooking(payload, employeeId, tripId); }

    //UPDATE BY ID <- modifica una prenotazione specifica
    @PutMapping("/{bookingId}")
    public Booking updateBooking(@PathVariable UUID bookingId, @RequestBody Booking payload)
    { return this.bookingService.findBookingByIdAndUpdate(bookingId, payload); }

    //DELETE BY ID <- elimina una prenotazione specifica
    @DeleteMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable UUID bookingId)
    { this.bookingService.findBookingByIdAndDelete(bookingId); }
}
