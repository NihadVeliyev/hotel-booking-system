package az.edu.turing.hotelbookingsystem.dto.Hotel;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class HotelRequest {


    @NotBlank
    private String name;

    @NotBlank
    private String location;
}
