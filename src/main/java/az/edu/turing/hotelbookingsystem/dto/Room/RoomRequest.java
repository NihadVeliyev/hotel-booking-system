package az.edu.turing.hotelbookingsystem.dto.Room;


import jakarta.validation.constraints.NotBlank;

public class RoomRequest {

    @NotBlank
    private  Integer number;

    @NotBlank
    private Integer price;

    @NotBlank
    private Long hotelId;


}
