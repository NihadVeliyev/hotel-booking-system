package az.edu.turing.hotelbookingsystem.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNoResource(NotFoundException ex,HttpServletRequest request){
        log.warn("Resource not found : {}",ex.getMessage());
        ApiError apiError=new ApiError(404, ex.getMessage(), request.getRequestURI(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex,HttpServletRequest request){


        List<FieldErrorResponse> fieldErrorResponseMap=ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err->new FieldErrorResponse(err.getField(),err.getDefaultMessage()))
                .toList();

        ApiError apiError=new ApiError(
                400,
                "Validition failed",
                request.getRequestURI(),
                LocalDateTime.now(),
                fieldErrorResponseMap

        );
        log.warn("Validation failed: {}", fieldErrorResponseMap);
        return ResponseEntity.badRequest().body(apiError);


    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleTypeMisMatch(MethodArgumentTypeMismatchException ex,HttpServletRequest request){
        String parameterName= ex.getName() !=null ? ex.getName() : "unknown";
        String wrongValue=String.valueOf(ex.getValue());
        String expectedType = ex.getRequiredType() != null
                ? ex.getRequiredType().getSimpleName()
                : "unknown";
        String message=String.format("Invalid value '%s' for parameter '%s', Expected value: %s.",wrongValue,parameterName,expectedType);
        log.warn("Type mismatch: {}",message);

        ApiError apiError=new ApiError(
                400,
                message,
                request.getRequestURI(),
                LocalDateTime.now()

        );

        return ResponseEntity.badRequest().body(apiError);


    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgument(IllegalArgumentException ex, HttpServletRequest request){
        log.warn("Invalid argument: {}", ex.getMessage());
        ApiError apiError = new ApiError(
                400,
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.badRequest().body(apiError);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiError> handleIllegalState(IllegalStateException ex, HttpServletRequest request){
        log.warn("Invalid state: {}", ex.getMessage());
        ApiError apiError = new ApiError(
                409,
                ex.getMessage(),
                request.getRequestURI(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(409).body(apiError);
    }





}
