package az.edu.turing.hotelbookingsystem.exceptions;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class ApiError {
    private int status;
    private String message;
    private String path;
    private LocalDateTime timestamp;

    public ApiError(int status, String message, String path, LocalDateTime timestamp) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = timestamp;
    }

}
