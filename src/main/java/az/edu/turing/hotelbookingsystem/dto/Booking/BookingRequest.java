package az.edu.turing.hotelbookingsystem.dto.Booking;


import jakarta.validation.constraints.*;
import lombok.Data;


import java.time.LocalDateTime;
@Data

public class BookingRequest {
    @Size(min=1,max=50)
    @NotBlank
    private String customerName;

    @Email
    @NotBlank
    private String customerEmail;

    private Long roomId;

    @FutureOrPresent
    private LocalDateTime startDate;

    @Future
    private LocalDateTime endDate;


}
