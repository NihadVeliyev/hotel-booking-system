package az.edu.turing.hotelbookingsystem.dto.Booking;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;
@Data
public class BookingRequest {
    @Size(min=1,max=50, message = "Customer name must be between 1 and 50 characters")
    @NotBlank(message = "Customer name is required")
    private String customerName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Customer email is required")
    private String customerEmail;

    @NotNull(message = "Room ID is required")
    @Positive(message = "Room ID must be positive")
    private Long roomId;

    @FutureOrPresent(message = "Start date must be present or in the future")
    @NotNull(message = "Start date is required")
    private LocalDateTime startDate;

    @Future(message = "End date must be in the future")
    @NotNull(message = "End date is required")
    private LocalDateTime endDate;


}