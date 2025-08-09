package az.edu.turing.hotelbookingsystem.mapper;

import az.edu.turing.hotelbookingsystem.dto.Booking.BookingRequest;
import az.edu.turing.hotelbookingsystem.dto.Booking.BookingResponse;
import az.edu.turing.hotelbookingsystem.entity.Booking;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingMapper {

    Booking toEntity(BookingRequest bookingRequest);
    BookingResponse toResponse(Booking booking);
}

