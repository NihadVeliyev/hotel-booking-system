package az.edu.turing.hotelbookingsystem.dto.Room;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoomRequest {

    @NotNull(message = "Room number is required")
    @Positive(message = "Room number must be positive")
    private Integer number;

    @NotNull(message = "Room price is required")
    @Positive(message = "Room price must be positive")
    private Integer price;

    @NotNull(message = "Hotel ID is required")
    @Positive(message = "Hotel ID must be positive")
    private Long hotelId;


}
