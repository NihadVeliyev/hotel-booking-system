package az.edu.turing.hotelbookingsystem.validation;

import az.edu.turing.hotelbookingsystem.dto.Booking.BookingRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateRangeValidator implements ConstraintValidator<ValidDateRange, BookingRequest> {

    @Override
    public void initialize(ValidDateRange constraintAnnotation) {
        // Initialization if needed
    }

    @Override
    public boolean isValid(BookingRequest bookingRequest, ConstraintValidatorContext context) {
        if (bookingRequest == null || bookingRequest.getStartDate() == null || bookingRequest.getEndDate() == null) {
            return true; // Let other validators handle null checks
        }
        
        return bookingRequest.getEndDate().isAfter(bookingRequest.getStartDate());
    }
}