package az.edu.turing.hotelbookingsystem.dto.Room;


import az.edu.turing.hotelbookingsystem.enums.RoomStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoomResponse {

    private Long id;

    private  Integer number;

    private Integer price;

    private RoomStatus status;

    private Long hotelId;

    private LocalDateTime createdAt;


}
