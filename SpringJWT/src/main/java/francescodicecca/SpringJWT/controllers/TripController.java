package francescodicecca.SpringJWT.controllers;

import francescodicecca.TravelManagement.entities.Trip;
import francescodicecca.TravelManagement.payloads.NewTripPayload;
import francescodicecca.TravelManagement.payloads.NewTripStatusPayload;
import francescodicecca.TravelManagement.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/trips")
public class TripController {
    @Autowired
    private TripService tripService;

    //GET <- lista paginata dei viaggi
    @GetMapping
    public Page<Trip> getAllTrips(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "date") String sort
    ) { return this.tripService.getTrip(page, size, sort); }

    //GET ONE BY ID <- singolo viaggio
    @GetMapping("/{tripId}")
    public Trip getTripById(@PathVariable UUID tripId) {
        return this.tripService.findTripById(tripId);
    }

    //CREATE <- nuovo viaggio
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Trip createTrip(@RequestBody @Validated NewTripPayload payload) {
        return this.tripService.saveTrip(payload);
    }

    //UPDATE BY ID <- modifica intero viaggio
    @PutMapping("/{tripId}")
    public Trip updateTrip(@PathVariable UUID tripId, @RequestBody Trip payload) {
        return this.tripService.update(tripId, payload);
    }

    //UPDATE STATUS ONLY BY ID <- modifica solo lo stato del viaggio
    @PatchMapping("/{tripId}/status")
    public Trip updateTripStatus(@PathVariable UUID tripId, @RequestBody @Validated NewTripStatusPayload payload) {
        return this.tripService.updateStatus(tripId, payload.getStatus());
    }

    //DELETE BY ID <- elimina un viaggio specifico
    @DeleteMapping("/{tripId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrip(@PathVariable UUID tripId) {
        this.tripService.findTripByIdAndDelete(tripId);
    }
}
