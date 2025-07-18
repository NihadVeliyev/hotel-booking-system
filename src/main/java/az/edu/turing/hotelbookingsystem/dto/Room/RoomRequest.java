package az.edu.turing.hotelbookingsystem.dto.Room;

import az.edu.turing.hotelbookingsystem.entity.Booking;
import az.edu.turing.hotelbookingsystem.entity.Hotel;
import az.edu.turing.hotelbookingsystem.enums.RoomStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

public class RoomRequest {

    @NotBlank
    private  Integer number;

    @NotBlank
    private Integer price;

    @NotBlank
    private Long hotelId;


}
