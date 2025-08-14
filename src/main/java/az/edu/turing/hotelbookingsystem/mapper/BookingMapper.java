package az.edu.turing.hotelbookingsystem.mapper;

import az.edu.turing.hotelbookingsystem.dto.Booking.BookingRequest;
import az.edu.turing.hotelbookingsystem.dto.Booking.BookingResponse;
import az.edu.turing.hotelbookingsystem.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "room", ignore = true)
    @Mapping(target = "bookingStatus", ignore = true)
    Booking toEntity(BookingRequest bookingRequest);
    
    @Mapping(target = "room.hotelId", source = "room.hotel.id")
    BookingResponse toResponse(Booking booking);
}

