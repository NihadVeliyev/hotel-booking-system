package az.edu.turing.hotelbookingsystem.dto.Booking;

import az.edu.turing.hotelbookingsystem.entity.Room;
import az.edu.turing.hotelbookingsystem.enums.BookingStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

public class BookingResponse {

    private Long id;

    private String customerName;

    private String customerEmail;

    private Room room;

    private LocalDateTime createdAt;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private BookingStatus bookinglStatus;

}
