package az.edu.turing.hotelbookingsystem.dto.Hotel;


import jakarta.validation.constraints.NotBlank;


public class HotelRequest {


    @NotBlank
    private String name;

    @NotBlank
    private String location;
}
