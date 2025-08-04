package az.edu.turing.hotelbookingsystem.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<ApiError> roomNotFound(RoomNotFoundException ex, HttpServletRequest request){
        log.warn("Room not found:{}",ex.getMessage());
        ApiError apiError=new ApiError(
                404,
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()

        );
        return ResponseEntity.status(404).body(apiError);

        
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> validationError(ValidationException ex,HttpServletRequest request){
        log.warn("Validation failed {}",ex.getMessage());
        ApiError apiError=new ApiError(
                400,
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(400).body(apiError);
    }

}
