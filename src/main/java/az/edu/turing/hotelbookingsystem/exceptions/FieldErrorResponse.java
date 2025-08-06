package az.edu.turing.hotelbookingsystem.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FieldErrorResponse {
    private String field;
    private String message;


}
