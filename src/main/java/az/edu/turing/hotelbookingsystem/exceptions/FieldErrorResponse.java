package az.edu.turing.hotelbookingsystem.exceptions;

import lombok.Data;

@Data
public class FieldErrorResponse {
    private String field;
    private String message;


}
