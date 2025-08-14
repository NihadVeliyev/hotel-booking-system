package az.edu.turing.hotelbookingsystem.controller;

import az.edu.turing.hotelbookingsystem.dto.Booking.BookingRequest;
import az.edu.turing.hotelbookingsystem.dto.Booking.BookingResponse;
import az.edu.turing.hotelbookingsystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> addBooking(@Validated @RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingService.addBooking(bookingRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id) {
        return ResponseEntity.ok(bookingService.getBookingById(id));
    }
    @GetMapping
    public ResponseEntity<List<BookingResponse>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookingById(@PathVariable Long id) {
        bookingService.deleteBookingById(id);
        return ResponseEntity.noContent().build();
    }
}