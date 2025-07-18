package az.edu.turing.hotelbookingsystem.dto.Hotel;



import lombok.Data;
import java.time.LocalDateTime;

@Data
public class HotelResponse {

    private Long id;


    private String name;


    private String location;


    private LocalDateTime createdAt;


}
