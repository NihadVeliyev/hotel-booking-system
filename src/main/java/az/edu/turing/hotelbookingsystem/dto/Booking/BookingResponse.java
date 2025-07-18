package az.edu.turing.hotelbookingsystem.dto.Booking;

import az.edu.turing.hotelbookingsystem.dto.Room.RoomResponse;
import az.edu.turing.hotelbookingsystem.enums.BookingStatus;


import java.time.LocalDateTime;

public class BookingResponse {

    private Long id;

    private String customerName;

    private String customerEmail;

    private RoomResponse room;

    private LocalDateTime createdAt;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private BookingStatus bookinglStatus;

}
