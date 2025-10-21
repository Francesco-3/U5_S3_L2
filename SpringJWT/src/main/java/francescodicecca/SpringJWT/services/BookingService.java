package francescodicecca.SpringJWT.services;

import francescodicecca.TravelManagement.entities.*;
import francescodicecca.TravelManagement.exceptions.*;
import francescodicecca.TravelManagement.payloads.NewBookingPayload;
import francescodicecca.TravelManagement.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private TripRepository tripRepository;

    //CREATE
    public Booking saveBooking(NewBookingPayload payload, UUID employeeId, UUID tripId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new NotFoundException(employeeId));
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new NotFoundException(tripId));

        List<Booking> existing = bookingRepository
                .findByEmployeeIdAndRequestDate(employeeId, payload.getRequestDate());

        if (!existing.isEmpty()) {
            log.warn("Prenotazione già esistente per il dipendente ID {} alla data {}", payload.getEmployeeId(), payload.getRequestDate());
            throw new BadRequestException("Esiste già una prenotazione per questo dipendente in questa data.");
        }

        Booking booking = new Booking();
        booking.setEmployee(employee);
        booking.setTrip(trip);
        booking.setRequestDate(payload.getRequestDate());
        booking.setNotes(payload.getNotes());

        log.info("Prenotazione creata con successo con ID: {}", booking.getId());
        return booking;
    }

    //READ
    public Page<Booking> getBooking(int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return bookingRepository.findAll(pageable);
    }

    public Booking findBookingById(UUID bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow(() -> new NotFoundException(bookingId));
    }

    //UPDATE
    public Booking findBookingByIdAndUpdate(UUID bookingId, Booking payload) {
        Booking found = this.findBookingById(bookingId);
        found.setRequestDate(payload.getRequestDate());
        found.setNotes(payload.getNotes());

        return bookingRepository.save(found);
    }

    //DELETE
    public void findBookingByIdAndDelete(UUID bookingId) {
        Booking found = this.findBookingById(bookingId);

        bookingRepository.delete(found);
    }
}