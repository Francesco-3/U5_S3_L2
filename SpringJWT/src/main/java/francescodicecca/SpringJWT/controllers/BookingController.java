package francescodicecca.SpringJWT.controllers;

import francescodicecca.SpringJWT.entities.Booking;
import francescodicecca.SpringJWT.payloads.NewBookingPayload;
import francescodicecca.SpringJWT.services.BookingService;
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

    @GetMapping
    public Page<Booking> getAllBookings(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "requestDate") String sort
    ) { return this.bookingService.getBooking(page, size, sort); }

    @GetMapping("/{bookingId}")
    public Booking getBookingById(@PathVariable UUID bookingId) {
        return this.bookingService.findBookingById(bookingId);
    }

    @PostMapping("/employee/{employeeId}/trip/{tripId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Booking createBooking(@PathVariable UUID employeeId, @PathVariable UUID tripId, @RequestBody @Validated NewBookingPayload payload)
    { return this.bookingService.saveBooking(payload, employeeId, tripId); }

    @PutMapping("/{bookingId}")
    public Booking updateBooking(@PathVariable UUID bookingId, @RequestBody Booking payload)
    { return this.bookingService.findBookingByIdAndUpdate(bookingId, payload); }

    @DeleteMapping("/{bookingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBooking(@PathVariable UUID bookingId)
    { this.bookingService.findBookingByIdAndDelete(bookingId); }
}
